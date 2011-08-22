package com.quadrictech.airqualitynow.service;

import java.io.IOException;

import org.codehaus.jackson.map.ObjectMapper;

import com.quadrictech.airqualitynow.R;
import com.quadrictech.airqualitynow.db.callback.IDataRequestCallback;
import com.quadrictech.airqualitynow.inet.callback.ForecastRemoteRequestCallback;
import com.quadrictech.airqualitynow.inet.callback.IRestRequestCallback;
import com.quadrictech.airqualitynow.inet.callback.ObservedRemoteRequestCallback;
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

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
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

	public IDataRequestCallback<Forecast> onGetForecastByZipCode(String zipCode){
		Time now = new Time();
		now.setToNow();
		
		mAirNowUrlParameter = new AirNowUrlParameter(mContext.getString(R.string.airnowgatewayforecastbyzipcodeurl),
				                                     zipCode, now.format("%Y-%m-%d"), "json", 
				                                     mContext.getString(R.string.airnowgateway_apikey));
		
		mAirNowUrl = new AirNowUrl(mAirNowUrlParameter);
		
		mForecastJsonProvider = new ForecastJsonProvider();
		
		IDataRequestCallback<Forecast> callback = null;
		
		try {
			callback = new ForecastRemoteRequestCallback();
			mRestRequestCallback = RestClient.executeHttpGet(mAirNowUrl);
			
			if(mRestRequestCallback.getErrorStatus()){
				throw new IOException("Error");
			}
			else{
				IForecastWrapper wrapper = mForecastJsonProvider.parseJson(new ObjectMapper(), 
						                                     mRestRequestCallback.getResponse().parseAsString());
				callback = new ForecastRemoteRequestCallback();
				callback.onResponseReceived(wrapper.getForecast());
			}
			
		} catch (IOException e) {
			callback.onError(new Throwable("Cannot retrieve remote data"));
		}
		
		return  callback;
	}

	public IDataRequestCallback<Observed> onGetObservedbyZipCode(String zipCode) {
		Time now = new Time();
		now.setToNow();
		mAirNowUrlParameter = new AirNowUrlParameter(mContext.getString(R.string.airnowgatewayobservedbyzipcode),
						        zipCode, now.format("%Y-%m-%d"), "json", 
						        mContext.getString(R.string.airnowgateway_apikey));
		
		mAirNowUrl = new AirNowUrl(mAirNowUrlParameter);
		
		mObservedJsonProvider = new ObservedJsonProvider();
		
		IDataRequestCallback<Observed> callback = null;
		
		try {
			callback = new ObservedRemoteRequestCallback();
			mRestRequestCallback = RestClient.executeHttpGet(mAirNowUrl);
			String json = mRestRequestCallback.getResponse().parseAsString();
			
			if(json.compareTo("{\"observed\": \"\"}") == 0){
				throw new IOException("No results found for zip code: " + zipCode);
			}
			
			IObservedWrapper wrapper = mObservedJsonProvider.parseJson(new ObjectMapper(), json);
			 
			callback.onResponseReceived(wrapper.getObserved());
		
		} catch (IOException e) {
			callback.onError(e);
		}
		
		return  callback;	
	}
}
