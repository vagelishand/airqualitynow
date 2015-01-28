package com.devterous.aqn.service;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.codehaus.jackson.map.ObjectMapper;

import com.devterous.aqn.R;
import com.devterous.aqn.db.callback.IDataRequestCallback;
import com.devterous.aqn.inet.callback.IRestRequestCallback;
import com.devterous.aqn.inet.callback.RemoteCallbackData;
import com.devterous.aqn.inet.callback.ReportingAreaRemoteRequestCallback;
import com.devterous.aqn.inet.rest.AirNowUrl;
import com.devterous.aqn.inet.rest.AirNowUrlParameter;
import com.devterous.aqn.inet.rest.RestClient;
import com.devterous.aqn.json.ForecastJsonProvider;
import com.devterous.aqn.json.IForecastJsonProvider;
import com.devterous.aqn.json.IObservationJsonProvider;
import com.devterous.aqn.json.ObservationJsonProvider;
import com.devterous.aqn.model.Forecast;
import com.devterous.aqn.model.IForecastWrapper;
import com.devterous.aqn.model.IObservationWrapper;
import com.devterous.aqn.model.Observation;
import com.devterous.aqn.model.ReportingArea;
import com.devterous.aqn.service.helper.DataProviderServiceHelper;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.os.AsyncTask;
import android.os.Binder;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.PowerManager;
import android.os.PowerManager.WakeLock;
import android.text.format.Time;
import android.util.Log;

public class RemoteDataProviderService extends Service implements IRemoteDataProviderService{
	AirNowUrl mAirNowUrl;
	AirNowUrlParameter mAirNowUrlParameter;
	IRestRequestCallback mRestRequestCallback;
	Context mContext;
	IForecastJsonProvider mForecastJsonProvider;
	IObservationJsonProvider mObservationJsonProvider;
	 private WakeLock mWakeLock;
	 
	
	/**
     * Class for clients to access.  Because we know this service always
     * runs in the same process as its clients, we don't need to deal with
     * IPC.
     */
    public class LocalBinder extends Binder {
        public RemoteDataProviderService getService() {
            return RemoteDataProviderService.this;
        }
    }
	
	private final IBinder mBinder = new LocalBinder();
	
	   private void handleIntent(Intent intent) {
	        // obtain the wake lock
	        PowerManager pm = (PowerManager) getSystemService(POWER_SERVICE);
	        mWakeLock = pm.newWakeLock(PowerManager.PARTIAL_WAKE_LOCK, getClass().getName());
	        mWakeLock.acquire();
	        
	        // check the global background data setting
	        ConnectivityManager cm = (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);
	        if (!cm.getBackgroundDataSetting()) {
	            stopSelf();
	            return;
	        }
	        
	        Log.d(getClass().getName(), "give me some work to do");
	        backgroundObservationUpdate();
	        backgroundForecastUpdate();
	    }
	    	
	
	@Override
	public IBinder onBind(Intent intent) {
		return mBinder;
	}
	
	@Override
    public int onStartCommand(Intent intent, int flags, int startId) {
		
		mContext = this.getBaseContext();
		
		if(intent.getBooleanExtra("FromAlarmManager", false)){
			handleIntent(intent);
		}
		
		return START_NOT_STICKY;
	}

	public List<Forecast> getForecastsByZipCode(String zipCode) throws IOException{
		Time now = new Time();
		now.setToNow();
		
		mAirNowUrlParameter = new AirNowUrlParameter(mContext.getString(R.string.airnowgatewayforecastbyzipcodeurl),
				                                     zipCode, now.format("%Y-%m-%d"), "application/json",
				                                     mContext.getString(R.string.airnowgateway_apikey));
		
		mAirNowUrl = new AirNowUrl(mAirNowUrlParameter);
		
		mForecastJsonProvider = new ForecastJsonProvider();
		
		mRestRequestCallback = RestClient.executeHttpGet(mAirNowUrl);
		
		String json = mRestRequestCallback.getResponse().parseAsString();
		
		if(json.compareTo("{\"forecast\": \"\"}") == 0){
			throw new IOException("Forecast results not available for zip code: " + zipCode);
		}
		
		IForecastWrapper wrapper = mForecastJsonProvider.parseJson(new ObjectMapper(),json);
		
		return wrapper.getForecast();
	}

	public List<Observation> getObservationsbyZipCode(String zipCode) throws IOException {
		Time now = new Time();
		now.setToNow();
		
		mAirNowUrlParameter = new AirNowUrlParameter(mContext.getString(R.string.airnowgatewayobservedbyzipcode),
						        zipCode, now.format("%Y-%m-%d"), "application/json",
						        mContext.getString(R.string.airnowgateway_apikey));
		
		mAirNowUrl = new AirNowUrl(mAirNowUrlParameter);
		
		mObservationJsonProvider = new ObservationJsonProvider();
		
		mRestRequestCallback = RestClient.executeHttpGet(mAirNowUrl);
		String json = mRestRequestCallback.getResponse().parseAsString();
		
		if(json.compareTo("{\"observed\": \"\"}") == 0){
			throw new IOException("Observation results not available for zip code: " + zipCode);
		}
		
		IObservationWrapper wrapper = mObservationJsonProvider.parseJson(new ObjectMapper(), json);
		
		return wrapper.getObserved();
	}

	public IDataRequestCallback<RemoteCallbackData> onGetReportingAreaByZipCode(String zipCode) {
		IDataRequestCallback<RemoteCallbackData> callback = new ReportingAreaRemoteRequestCallback();
		List<Observation> observations = null;
		StringBuilder errorMsg = new StringBuilder();		
		
		try {
			observations = getObservationsbyZipCode(zipCode);
		} catch (IOException e) {
			errorMsg.append(e.getLocalizedMessage());
		}
		GetForecastThread thread = new GetForecastThread(zipCode);		
		thread.start();
		
		try {
			thread.join();
		} catch (InterruptedException e) {
			errorMsg.append(e.getLocalizedMessage());
		}

		if(errorMsg.length() > 0){
			callback.onError(new Throwable(errorMsg.toString()));
			return callback;
		}
		
		ReportingArea area = new ReportingArea();
		Observation observation = observations.get(0);
		
		if(mForecasts.size() > 0){
			Forecast forecast = mForecasts.get(0);
			area.ForecastAQI = forecast.AQI;
		}
		
		area.Name = observation.ReportingArea;
		area.ObservedAQI = observation.AQI;
		
		area.State = observation.StateCode;
		area.ZipCode = zipCode;
		area.Latitude = (int) (observation.Latitude * 1E6);
		area.Longitude = (int) (observation.Longitude * 1E6);
		
		try {
			area = DataProviderServiceHelper.getInstance().insertReportingArea(area);
		} catch (SQLException e) {
			errorMsg.append(e.getLocalizedMessage());
		}
		
		if(errorMsg.length() > 0){
			callback.onError(new Throwable(errorMsg.toString()));
			return callback;
		}		
		
		RemoteCallbackData callbackData = new RemoteCallbackData();
		List<RemoteCallbackData> list = new ArrayList<RemoteCallbackData>();		
		callbackData.reportingArea = area;
		callbackData.forecasts = mForecasts;
		callbackData.observations = observations;
		list.add(callbackData);
		
		callback.onResponseReceived(list);
		
		return callback;
	}
	
	private List<Forecast> mForecasts;
	
	private final Handler handler = new Handler(){
		@SuppressWarnings("unchecked")
		@Override
		public void handleMessage(Message msg){
			mForecasts = (List<Forecast>) msg.obj;
		}
	};

	/**
     * In onDestroy() we release our wake lock. This ensures that whenever the
     * Service stops (killed for resources, stopSelf() called, etc.), the wake
     * lock will be released.
     */
    public void onDestroy() {
        super.onDestroy();
        
        if(mWakeLock != null){
        	mWakeLock.release();
        }
    }	
	
	class GetForecastThread extends Thread{
		private String mZipCode;
		
		public GetForecastThread(String zipCode){
			mZipCode = zipCode;
		}
				
		public void run(){
			try {
				Message msg = Message.obtain();
				msg.obj = getForecastsByZipCode(mZipCode);
				handler.sendMessage(msg);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public void backgroundForecastUpdate(){
		UpdateForecastsTask task = new UpdateForecastsTask();
		task.execute();
	}
	
	private class UpdateForecastsTask extends AsyncTask<Void, Void, Void>{

		@Override
		protected Void doInBackground(Void... arg0) {
			IDataRequestCallback<ReportingArea> callback = DataProviderServiceHelper.getInstance().getAllReportingAreas();
			
			if(!callback.getErrorStatus()){
				List<ReportingArea> areas= callback.getList();
				
				for(ReportingArea area: areas){
					try {
						area.DateStamp = new Date();
						DataProviderServiceHelper.getInstance().updateReportingArea(area);
						List<Forecast> forecasts = getForecastsByZipCode(area.ZipCode);
						DataProviderServiceHelper.getInstance().insertForecasts(area, forecasts);
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
			
			return null;
		}
		
		@Override
		protected void onPostExecute(Void result){
			stopSelf();
		}
		
	}
	
	public void backgroundObservationUpdate() {
		UpdateObservationsTask task = new UpdateObservationsTask();
		task.execute();
	}
	
	private class UpdateObservationsTask extends AsyncTask<Void, Void, Void> {
        @Override
        protected Void doInBackground(Void... params) {
            IDataRequestCallback<ReportingArea> callback = DataProviderServiceHelper.getInstance().getAllReportingAreas();
            
            if(!callback.getErrorStatus()){
            	List<ReportingArea> areas = callback.getList();
            	
            	for(ReportingArea area: areas){
            		try {
            			area.DateStamp = new Date();
            			DataProviderServiceHelper.getInstance().updateReportingArea(area);
						List<Observation> observations = getObservationsbyZipCode(area.ZipCode);
						DataProviderServiceHelper.getInstance().insertObservations(area, observations);
					} catch (IOException e) {
						e.printStackTrace();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
            	}
            }
            
            return null;
        }
        
        @Override
        protected void onPostExecute(Void result) {
            // handle your data
            stopSelf();
        }
    }	
}
