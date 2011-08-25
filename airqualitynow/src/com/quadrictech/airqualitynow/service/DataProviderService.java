package com.quadrictech.airqualitynow.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

import com.j256.ormlite.android.apptools.OrmLiteBaseService;
import com.j256.ormlite.dao.Dao;
import com.quadrictech.airqualitynow.db.AppRepository;
import com.quadrictech.airqualitynow.db.DatabaseHelper;
import com.quadrictech.airqualitynow.db.IForecastRepository;
import com.quadrictech.airqualitynow.db.IObservedRepository;
import com.quadrictech.airqualitynow.db.IPollutantRepository;
import com.quadrictech.airqualitynow.db.IReportingAreaRepository;
import com.quadrictech.airqualitynow.db.callback.ForecastRequestCallback;
import com.quadrictech.airqualitynow.db.callback.IDataRequestCallback;
import com.quadrictech.airqualitynow.db.callback.ObservedAndForecastRequestCallback;
import com.quadrictech.airqualitynow.db.callback.ObservedRequestCallback;
import com.quadrictech.airqualitynow.db.callback.ReportingAreaRequestCallback;
import com.quadrictech.airqualitynow.model.Forecast;
import com.quadrictech.airqualitynow.model.Observed;
import com.quadrictech.airqualitynow.model.Pollutant;
import com.quadrictech.airqualitynow.model.ReportingArea;
import com.quadrictech.airqualitynow.model.viewmodel.ObservedAndForecast;

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
	private IForecastRepository mForecastRepository;
	private IReportingAreaRepository mReportingAreaRepository;
	private IObservedRepository mObservedRepository;
	private IPollutantRepository mPollutantRepository;
	
	@Override
	public IBinder onBind(Intent intent) {
		return mBinder;
	}
	
	@Override
    public int onStartCommand(Intent intent, int flags, int startId) {
		
		return START_NOT_STICKY;
	}
	
	public void initialize(IForecastRepository fr) {
		mForecastRepository = fr;
	}
	
	public void initialize( IReportingAreaRepository rar){
		mReportingAreaRepository = rar;
	}

	public void initialize(IObservedRepository or) {
		mObservedRepository = or;		
	}

	public IDataRequestCallback<ReportingArea> getAllReportingAreas(){
		IDataRequestCallback<ReportingArea> callback = new ReportingAreaRequestCallback();
		try {
			if(mReportingAreaRepository == null){
				mReportingAreaRepository = new AppRepository(getHelper().getConnectionSource()).ReportingAreaRepository();
			}
						
			List<ReportingArea> reportingAreas = mReportingAreaRepository.getAll();
			
			callback.onResponseReceived(reportingAreas);
			
		} catch (SQLException e) {
			callback.onError(e);
		}
		
		return callback;
	}
	
	public IDataRequestCallback<Forecast> getAllForecasts(){
		IDataRequestCallback<Forecast> callback = new ForecastRequestCallback();
		
		try {
			if(mForecastRepository == null){
				mForecastRepository = new AppRepository(getHelper().getConnectionSource()).ForecastRepository();
			}
			
			List<Forecast> forecasts = mForecastRepository.getAll();
			callback.onResponseReceived(forecasts);
			
		} catch (SQLException e) {
			callback.onError(e);
		}
		
		return callback;
	}
	
	public IDataRequestCallback<ReportingArea> insertReportingArea(ReportingArea reportingArea) {
		IDataRequestCallback<ReportingArea> callback = new ReportingAreaRequestCallback();
		
		try {
			if(mReportingAreaRepository == null){
				mReportingAreaRepository = new AppRepository(getHelper().getConnectionSource()).ReportingAreaRepository();
			}
			mReportingAreaRepository.insert(reportingArea);
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
			if(mReportingAreaRepository == null){
				mReportingAreaRepository = new AppRepository(getHelper().getConnectionSource()).ReportingAreaRepository();
			}
			
			List<ReportingArea> areas = mReportingAreaRepository.getByFieldEquals("ZipCode", zipCode);
			
			callback.onResponseReceived(areas);
		} catch (SQLException e) {
			callback.onError(new Throwable("Unable to retrieve Reporting Area: " + e.getMessage()));
		}
		
		return callback;
	}
	
	public IDataRequestCallback<Observed> insertObserved(List<Observed> observedList) {
		IDataRequestCallback<Observed> callback = new ObservedRequestCallback();
		
		try{
			if(mObservedRepository == null){
				mObservedRepository = new AppRepository(getHelper().getConnectionSource()).ObservedRepository();
			}
			
			for(Observed o: observedList){
				o.Pollutant = getPollutantByName(o.ParameterName);
				mObservedRepository.insert(o);
			}
		}
		catch(SQLException e){
			callback.onError(e);
		}
		
		return callback;
	}

	public IDataRequestCallback<Forecast> insertForecasts(List<Forecast> forecasts) {
		IDataRequestCallback<Forecast> callback = new ForecastRequestCallback();
		Forecast f1 = new Forecast();
		try {
			if(mForecastRepository == null){
				mForecastRepository = new AppRepository(getHelper().getConnectionSource()).ForecastRepository();
			}
			
			for(Forecast f: forecasts){
				f1.ParameterName = f.ParameterName;
				f.Pollutant = getPollutantByName(f.ParameterName);
				mForecastRepository.insert(f);
			}
			
		} catch (SQLException e) {
			callback.onError(e);
			Log.d("service", f1.ParameterName);
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
			if(mForecastRepository == null){
				mForecastRepository = new AppRepository(getHelper().getConnectionSource()).ForecastRepository();
			}
			
			List<Forecast> forecasts = mForecastRepository.getQueryResults(
				mForecastRepository.getQueryBuilder().where().
			    eq("ReportingAreaObject_id", id).
			    and().
			    ge("DateIssue", issueDate).prepare());
			
			callback.onResponseReceived(forecasts);
			
		} catch (SQLException e) {
			callback.onError(e);
		}
		
		return callback;
	}
	
	public IDataRequestCallback<Observed> getObservedByReportingAreaId(int id, Date date) {
		IDataRequestCallback<Observed> callback = new ObservedRequestCallback();
		
		try {
			if(mObservedRepository == null){
				mObservedRepository = new AppRepository(getHelper().getConnectionSource()).ObservedRepository();
			}
			
			List<Observed> observed = mObservedRepository.getQueryResults(
				mObservedRepository.getQueryBuilder().where().
				eq("ReportingAreaObject_id", id).
				and().
				eq("DateObserved", date).prepare());
			
			callback.onResponseReceived(observed);
		} catch (SQLException e) {
			callback.onError(e);
		} 
		
		return callback;
	}

	public IDataRequestCallback<ObservedAndForecast> getObservedAndForecastByReportingArea(int id, Date observedDate) {
		IDataRequestCallback<Observed> oCallback = new ObservedRequestCallback();
		IDataRequestCallback<Forecast> fCallback = new ForecastRequestCallback();
		
		IDataRequestCallback<ObservedAndForecast> ofCallback = new ObservedAndForecastRequestCallback();
		
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
		
		ObservedAndForecast o = new ObservedAndForecast();
		o.Forecasts = fCallback.getList();
		o.ObservedList = oCallback.getList();
		
		List<ObservedAndForecast> list= new ArrayList<ObservedAndForecast>();
		list.add(o);
		
		ofCallback.onResponseReceived(list);
		
		return ofCallback;
	}

	public Pollutant getPollutantByName(String name) throws SQLException {
		if(mPollutantRepository == null){
			mPollutantRepository = new AppRepository(getHelper().getConnectionSource()).PollutantRepository();
		}
		
		Pollutant pollutant = null;
		
		List<Pollutant> pollutants = mPollutantRepository.getByFieldEquals("Name", name);
		if(pollutants.size() > 0){
			pollutant = pollutants.get(0);
		}
		
		return pollutant;
	}	
}
