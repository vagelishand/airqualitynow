package com.quadrictech.airqualitynow.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

import com.j256.ormlite.android.apptools.OrmLiteBaseService;
import com.j256.ormlite.dao.Dao;
import com.quadrictech.airqualitynow.db.AppRepository;
import com.quadrictech.airqualitynow.db.DatabaseHelper;
import com.quadrictech.airqualitynow.db.IAppRepository;
import com.quadrictech.airqualitynow.db.callback.ForecastRequestCallback;
import com.quadrictech.airqualitynow.db.callback.IDataRequestCallback;
import com.quadrictech.airqualitynow.db.callback.ObservationAndForecastRequestCallback;
import com.quadrictech.airqualitynow.db.callback.ObservationRequestCallback;
import com.quadrictech.airqualitynow.db.callback.PollutantRequestCallback;
import com.quadrictech.airqualitynow.db.callback.ReportingAreaRequestCallback;
import com.quadrictech.airqualitynow.model.Forecast;
import com.quadrictech.airqualitynow.model.Observation;
import com.quadrictech.airqualitynow.model.Pollutant;
import com.quadrictech.airqualitynow.model.ReportingArea;
import com.quadrictech.airqualitynow.model.viewmodel.ObservationAndForecast;

public class DataProviderService extends OrmLiteBaseService<DatabaseHelper> implements IDataProviderService {
	/**
     * Class for clients to access.  Because we know this service always
     * runs in the same process as its clients, we don't need to deal with
     * IPC.
     */
    public class LocalBinder extends Binder {
        public DataProviderService getService() {
            return DataProviderService.this;
        }
    }
	
	private final IBinder mBinder = new LocalBinder();
	private IAppRepository mAppRepository;
	
	@Override
	public IBinder onBind(Intent intent) {
		return mBinder;
	}
	
	@Override
    public int onStartCommand(Intent intent, int flags, int startId) {
		
		if(!intent.getBooleanExtra("UnitTest", false)){
			mAppRepository = new AppRepository(getHelper().getConnectionSource());
		}
		
		return START_NOT_STICKY;
	}
	
	public IDataRequestCallback<ReportingArea> getAllReportingAreas(){
		IDataRequestCallback<ReportingArea> callback = new ReportingAreaRequestCallback();
		try {
			List<ReportingArea> reportingAreas = mAppRepository.ReportingAreaRepository().getAll();
			
			callback.onResponseReceived(reportingAreas);
			
		} catch (SQLException e) {
			callback.onError(e);
		}
		
		return callback;
	}
	
	public IDataRequestCallback<Forecast> getAllForecasts(){
		IDataRequestCallback<Forecast> callback = new ForecastRequestCallback();
		
		try {
			List<Forecast> forecasts = mAppRepository.ForecastRepository().getAll();
			
			callback.onResponseReceived(forecasts);
			
		} catch (SQLException e) {
			callback.onError(e);
		}
		
		return callback;
	}
	
	public IDataRequestCallback<ReportingArea> insertReportingArea(ReportingArea reportingArea) {
		IDataRequestCallback<ReportingArea> callback = new ReportingAreaRequestCallback();
		
		try {
			reportingArea.DateStamp = new Date();
			
			mAppRepository.ReportingAreaRepository().insert(reportingArea);
			List<ReportingArea> areas = new ArrayList<ReportingArea>(1);
			areas.add(reportingArea);
			callback.onResponseReceived(areas);
		} catch (SQLException e) {
			callback.onError(new Throwable("Unable to insert Reporting Area"));
		}
		
		return callback;
	}

	public IDataRequestCallback<ReportingArea> getReportingAreaByZipCode(String zipCode) {
		IDataRequestCallback<ReportingArea> callback = new ReportingAreaRequestCallback();
		
		try {
			List<ReportingArea> areas = mAppRepository.ReportingAreaRepository().getByFieldEquals("ZipCode", zipCode);
			
			callback.onResponseReceived(areas);
		} catch (SQLException e) {
			callback.onError(new Throwable("Unable to retrieve Reporting Area: " + e.getMessage()));
		}
		
		return callback;
	}
	
	public IDataRequestCallback<Observation> insertObservations(ReportingArea reportingArea, List<Observation> observations) {
		IDataRequestCallback<Observation> callback = new ObservationRequestCallback();
		
		try{
			
			ReportingArea area = mAppRepository.ReportingAreaRepository().getById(reportingArea.Id);
			area.ObservedAQI = observations.get(0).AQI;
			updateReportingArea(area);
			
			for(Observation o: observations){
				o.ReportingAreaObject = reportingArea;
				o.Pollutant = getPollutantByName(o.ParameterName.trim());
				mAppRepository.ObservationRepository().insert(o);
			}
			
			callback.onResponseReceived(observations);
		}
		catch(SQLException e){
			callback.onError(e);
		}
		
		return callback;
	}

	public IDataRequestCallback<Forecast> insertForecasts(ReportingArea reportingArea, List<Forecast> forecasts) {
		IDataRequestCallback<Forecast> callback = new ForecastRequestCallback();
		
		try {
			
			for(Forecast f: forecasts){
				f.ReportingAreaObject = reportingArea;
				f.Pollutant = getPollutantByName(f.ParameterName.trim());
				mAppRepository.ForecastRepository().insert(f);
			}
			
		} catch (SQLException e) {
			callback.onError(e);
		}
		
		return callback;	
	}

	public ReportingArea insertReportArea(ReportingArea reportingArea)throws SQLException {
		ReportingArea area = reportingArea;
		Dao<ReportingArea, Integer> dao = getHelper().getReportingAreaDAO();
		List<ReportingArea>areas = dao.queryForEq("Name", area.Name);
		
		if(areas.size() > 0){
			throw new SQLException(area.ZipCode + " data available via " + area.Name);
		}
		
		dao.create(area);
		
		return area;
	}

	public IDataRequestCallback<Forecast> getForecastsByReportingAreaId(int id, Date issueDate) {
		IDataRequestCallback<Forecast> callback = new ForecastRequestCallback();
		
		try {
			
			List<Forecast> forecasts = mAppRepository.ForecastRepository().getQueryResults(
				mAppRepository.ForecastRepository().getQueryBuilder().where().
			    eq("ReportingAreaObject_id", id).
			    and().
			    ge("DateForecast", issueDate).prepare());
			
			callback.onResponseReceived(forecasts);
			
		} catch (SQLException e) {
			callback.onError(e);
		}
		
		return callback;
	}
	
	public IDataRequestCallback<Observation> getObservedByReportingAreaId(int id, Date date) {
		IDataRequestCallback<Observation> callback = new ObservationRequestCallback();
		
		try {
			
			List<Observation> observed = mAppRepository.ObservationRepository().getQueryResults(
				mAppRepository.ObservationRepository().getQueryBuilder().where().
				eq("ReportingAreaObject_id", id).
				and().
				eq("DateObserved", date).prepare());
			
			callback.onResponseReceived(observed);
		} catch (SQLException e) {
			callback.onError(e);
		}
		return callback;
	}

	public IDataRequestCallback<ObservationAndForecast> getObservedAndForecastByReportingArea(int id, Date observedDate) {
		IDataRequestCallback<Observation> oCallback = new ObservationRequestCallback();
		IDataRequestCallback<Forecast> fCallback = new ForecastRequestCallback();
		
		IDataRequestCallback<ObservationAndForecast> ofCallback = new ObservationAndForecastRequestCallback();
		
		if(oCallback.getErrorStatus()){
			ofCallback.onError(new Throwable(oCallback.getErrorMessage()));
			
			return ofCallback;
		}
		
		if(fCallback.getErrorStatus()){
			ofCallback.onError(new Throwable(fCallback.getErrorMessage()));
			
			return ofCallback;
		}
		
		oCallback = this.getObservedByReportingAreaId(id, observedDate);
		fCallback = this.getForecastsByReportingAreaId(id, observedDate);
		
		ObservationAndForecast o = new ObservationAndForecast();
		o.Forecasts = fCallback.getList();
		o.ObservedList = oCallback.getList();
		
		List<ObservationAndForecast> list= new ArrayList<ObservationAndForecast>();
		list.add(o);
		
		ofCallback.onResponseReceived(list);
		
		return ofCallback;
	}

	public Pollutant getPollutantByName(String name) throws SQLException {
		
		Pollutant pollutant = null;
		
		List<Pollutant> pollutants = mAppRepository.PollutantRepository().getByFieldEquals("Name", name);
		if(pollutants.size() > 0){
			pollutant = pollutants.get(0);
		}
		
		return pollutant;
	}

	public void updateReportingArea(ReportingArea reportingArea) throws SQLException {
		mAppRepository.ReportingAreaRepository().update(reportingArea);
	}

	public IDataRequestCallback<Pollutant> getPollutants() {
		IDataRequestCallback<Pollutant> callback = new PollutantRequestCallback();
		
		
		try {
			List<Pollutant> pollutants = mAppRepository.PollutantRepository().getAll();
			callback.onResponseReceived(pollutants);
		} catch (SQLException e) {
			callback.onError(new Throwable("Error retrieving pollutants"));
		}
		

		return callback;
	}

	public void initialize(IAppRepository appRepository) {
		this.mAppRepository = appRepository;
	}

}
