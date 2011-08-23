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
import com.quadrictech.airqualitynow.json.IObservedJsonProvider;
import com.quadrictech.airqualitynow.json.ObservedJsonProvider;
import com.quadrictech.airqualitynow.model.Forecast;
import com.quadrictech.airqualitynow.model.IForecastWrapper;
import com.quadrictech.airqualitynow.model.IObservedWrapper;
import com.quadrictech.airqualitynow.model.Observed;
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
	IObservedJsonProvider mObservedJsonProvider;
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

	public List<Forecast> onGetForecastByZipCode(String zipCode) throws IOException{
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

	public List<Observed> onGetObservedbyZipCode(String zipCode) throws IOException {
		Time now = new Time();
		now.setToNow();
		
		mAirNowUrlParameter = new AirNowUrlParameter(mContext.getString(R.string.airnowgatewayobservedbyzipcode),
						        zipCode, /*now.format("%Y-%m-%d")*/"2011-08-22", "json", 
						        mContext.getString(R.string.airnowgateway_apikey));
		
		mAirNowUrl = new AirNowUrl(mAirNowUrlParameter);
		
		mObservedJsonProvider = new ObservedJsonProvider();
		
		mRestRequestCallback = RestClient.executeHttpGet(mAirNowUrl);
		String json = mRestRequestCallback.getResponse().parseAsString();
		
		if(json.compareTo("{\"observed\": \"\"}") == 0){
			throw new IOException("Observed results not available for zip code: " + zipCode);
		}
		
		IObservedWrapper wrapper = mObservedJsonProvider.parseJson(new ObjectMapper(), json);
		
		return wrapper.getObserved();
	}

	public IDataRequestCallback<RemoteCallbackData> onGetReportingAreaByZipCode(String zipCode) {
		IDataRequestCallback<RemoteCallbackData> callback = new ReportingAreaRemoteRequestCallback();
		List<Observed> observedList = null;
		StringBuilder errorMsg = new StringBuilder();		
		
		try {
			observedList = onGetObservedbyZipCode(zipCode);
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
		Observed observed = observedList.get(0);
		Forecast forecast = mForecasts.get(0);
		
		area.Name = observed.ReportingArea;
		area.ObservedAQI = observed.AQI;
		area.ForecastAQI = forecast.AQI;
		area.State = observed.StateCode;
		area.ZipCode = zipCode;
		
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
		callbackData.observed = observedList;
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
				msg.obj = onGetForecastByZipCode(mZipCode);
				handler.sendMessage(msg);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
