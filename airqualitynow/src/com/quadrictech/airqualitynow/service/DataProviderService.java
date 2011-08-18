package com.quadrictech.airqualitynow.service;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

import com.j256.ormlite.android.apptools.OrmLiteBaseService;
import com.quadrictech.airqualitynow.db.AppRepository;
import com.quadrictech.airqualitynow.db.DatabaseHelper;
import com.quadrictech.airqualitynow.db.IForecastRepository;
import com.quadrictech.airqualitynow.db.IObservedRepository;
import com.quadrictech.airqualitynow.db.IReportingAreaRepository;
import com.quadrictech.airqualitynow.db.callback.ForecastRequestCallback;
import com.quadrictech.airqualitynow.db.callback.ILocalRequestCallback;
import com.quadrictech.airqualitynow.db.callback.ObservedRequestCallback;
import com.quadrictech.airqualitynow.db.callback.ReportingAreaRequestCallback;
import com.quadrictech.airqualitynow.model.Forecast;
import com.quadrictech.airqualitynow.model.Observed;
import com.quadrictech.airqualitynow.model.ReportingArea;
import com.quadrictech.airqualitynow.model.State;

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
		
	@Override
	public IBinder onBind(Intent intent) {
		return mBinder;
	}
	
	@Override
    public int onStartCommand(Intent intent, int flags, int startId) {
		State state = new State();
		state.Name = "TX";
		
		try{
			getHelper().getStateDAO().create(state);
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		
		return START_NOT_STICKY;
	}
	
	public ILocalRequestCallback<ReportingArea> onGetAllReportingAreas(){
		ILocalRequestCallback<ReportingArea> callback = new ReportingAreaRequestCallback();
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
	
	public ILocalRequestCallback<Forecast> onGetAllForecasts(){
		ILocalRequestCallback<Forecast> callback = new ForecastRequestCallback();
		
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
	
	public ILocalRequestCallback<Observed> onGetObservedByDate(Date date) {
		ILocalRequestCallback<Observed> callback = new ObservedRequestCallback();
		
		try {
			if(mObservedRepository == null){
				mObservedRepository = new AppRepository(getHelper().getConnectionSource()).ObservedRepository();
			}
			
			List<Observed> observed = mObservedRepository.getByFieldEquals("DateObserved", date);
			callback.onResponseReceived(observed);
		} catch (SQLException e) {
			callback.onError(e);
		} 
		
		return callback;
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
	
	
}
