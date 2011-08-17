package com.quadrictech.airqualitynow.service;

import java.sql.SQLException;
import java.util.List;

import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

import com.j256.ormlite.android.apptools.OrmLiteBaseService;
import com.quadrictech.airqualitynow.db.AppRepository;
import com.quadrictech.airqualitynow.db.DatabaseHelper;
import com.quadrictech.airqualitynow.db.IForecastRepository;
import com.quadrictech.airqualitynow.db.IReportingAreaRepository;
import com.quadrictech.airqualitynow.db.callback.ForecastRequestCallback;
import com.quadrictech.airqualitynow.db.callback.ILocalRequestCallback;
import com.quadrictech.airqualitynow.db.callback.ReportingAreaRequestCallback;
import com.quadrictech.airqualitynow.model.Forecast;
import com.quadrictech.airqualitynow.model.ForecastWrapper;
import com.quadrictech.airqualitynow.model.Observed;
import com.quadrictech.airqualitynow.model.ReportingArea;
import com.quadrictech.airqualitynow.model.ReportingAreaWrapper;
import com.quadrictech.airqualitynow.model.State;

public class DataProviderService extends OrmLiteBaseService<DatabaseHelper> implements IDataProviderService {
	private IForecastRepository mForecastRepository;
	
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
		try {
			IReportingAreaRepository repository = new AppRepository(getHelper().getConnectionSource()).ReportingAreaRepository();
						
			List<ReportingArea> reportingAreas = repository.getAll();
			
			return new ReportingAreaRequestCallback(new ReportingAreaWrapper(reportingAreas)); 
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public ILocalRequestCallback<Forecast> onGetAllForecasts(){
		
		mForecastRepository = new AppRepository(getHelper().getConnectionSource()).ForecastRepository();
		try {
			List<Forecast> forecasts = mForecastRepository.getAll();
			ILocalRequestCallback<Forecast> callback = new ForecastRequestCallback(new ForecastWrapper(forecasts));
			
			return callback;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}
	
	public ILocalRequestCallback<Forecast> onGetForecastById(int id){
		mForecastRepository = new AppRepository(getHelper().getConnectionSource()).ForecastRepository();
		
		try {
			Forecast forecast = mForecastRepository.getById(id);
			ILocalRequestCallback<Forecast> callback = new ForecastRequestCallback(forecast);
			
			return callback;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}

	public ILocalRequestCallback<Observed> onGetObservedByZipCode(String zipCode) {
		// TODO Auto-generated method stub
		return null;
	}
}
