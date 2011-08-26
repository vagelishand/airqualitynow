package com.quadrictech.airqualitynow.service;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.codehaus.jackson.map.ObjectMapper;

import com.quadrictech.airqualitynow.R;
import com.quadrictech.airqualitynow.db.callback.IDataRequestCallback;
import com.quadrictech.airqualitynow.inet.callback.IRestRequestCallback;
import com.quadrictech.airqualitynow.inet.callback.RemoteCallbackData;
import com.quadrictech.airqualitynow.inet.callback.ReportingAreaRemoteRequestCallback;
import com.quadrictech.airqualitynow.inet.rest.AirNowUrl;
import com.quadrictech.airqualitynow.inet.rest.AirNowUrlParameter;
import com.quadrictech.airqualitynow.inet.rest.RestClient;
import com.quadrictech.airqualitynow.json.ForecastJsonProvider;
import com.quadrictech.airqualitynow.json.IForecastJsonProvider;
import com.quadrictech.airqualitynow.json.IObservationJsonProvider;
import com.quadrictech.airqualitynow.json.ObservationJsonProvider;
import com.quadrictech.airqualitynow.model.Forecast;
import com.quadrictech.airqualitynow.model.IForecastWrapper;
import com.quadrictech.airqualitynow.model.IObservationWrapper;
import com.quadrictech.airqualitynow.model.Observation;
import com.quadrictech.airqualitynow.model.ReportingArea;
import com.quadrictech.airqualitynow.service.helper.DataProviderServiceHelper;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Binder;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.text.format.Time;

public class RemoteDataProviderService extends Service implements IRemoteDataProviderService{
	AirNowUrl mAirNowUrl;
	AirNowUrlParameter mAirNowUrlParameter;
	IRestRequestCallback mRestRequestCallback;
	Context mContext;
	IForecastJsonProvider mForecastJsonProvider;
	IObservationJsonProvider mObservationJsonProvider;
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
	
	@Override
	public IBinder onBind(Intent intent) {
		return mBinder;
	}
	
	@Override
    public int onStartCommand(Intent intent, int flags, int startId) {
		
		mContext = this.getBaseContext();
		
		return START_NOT_STICKY;
	}

	public List<Forecast> onGetForecastsByZipCode(String zipCode) throws IOException{
		Time now = new Time();
		now.setToNow();
		
		mAirNowUrlParameter = new AirNowUrlParameter(mContext.getString(R.string.airnowgatewayforecastbyzipcodeurl),
				                                     zipCode, now.format("%Y-%m-%d"), "json", 
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
						        zipCode, now.format("%Y-%m-%d"), "json", 
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
		Forecast forecast = mForecasts.get(0);
		
		area.Name = observation.ReportingArea;
		area.ObservedAQI = observation.AQI;
		area.ForecastAQI = forecast.AQI;
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
	
	class GetForecastThread extends Thread{
		private String mZipCode;
		
		public GetForecastThread(String zipCode){
			mZipCode = zipCode;
		}
				
		public void run(){
			try {
				Message msg = Message.obtain();
				msg.obj = onGetForecastsByZipCode(mZipCode);
				handler.sendMessage(msg);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
