package com.quadrictech.airqualitynow.service;

import java.io.IOException;

import org.codehaus.jackson.map.ObjectMapper;

import com.quadrictech.airqualitynow.R;
import com.quadrictech.airqualitynow.inet.callback.ForecastRemoteRequestCallback;
import com.quadrictech.airqualitynow.inet.callback.IRemoteRequestCallback;
import com.quadrictech.airqualitynow.inet.callback.IRestRequestCallback;
import com.quadrictech.airqualitynow.inet.rest.AirNowUrl;
import com.quadrictech.airqualitynow.inet.rest.AirNowUrlParameter;
import com.quadrictech.airqualitynow.inet.rest.RestClient;
import com.quadrictech.airqualitynow.json.ForecastJsonProvider;
import com.quadrictech.airqualitynow.json.IForecastJsonProvider;
import com.quadrictech.airqualitynow.model.Forecast;
import com.quadrictech.airqualitynow.model.ForecastWrapper;
import com.quadrictech.airqualitynow.model.IForecastWrapper;
import com.quadrictech.airqualitynow.model.ReportingArea;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;

public class RemoteDataProviderService extends Service implements IRemoteDataProviderService{
	AirNowUrl mAirNowUrl;
	AirNowUrlParameter mAirNowUrlParameter;
	IRestRequestCallback mRestRequestCallback;
	Context mContext;
	IForecastJsonProvider mForecastJsonProvider;
	
	@Override
	public IBinder onBind(Intent arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	public IRemoteRequestCallback<Forecast> onGetForecastByZipCode(String zipCode) {
		mAirNowUrlParameter = new AirNowUrlParameter(mContext.getString(R.string.airnowgatewayforecastbyzipcodeurl),
				                                     zipCode, "", "json", 
				                                     mContext.getString(R.string.airnowgateway_apikey));
		mAirNowUrl = new AirNowUrl(mAirNowUrlParameter);
		mRestRequestCallback = RestClient.executeHttpGet(mAirNowUrl);
		
		mForecastJsonProvider = new ForecastJsonProvider();
		
		IRemoteRequestCallback<Forecast> callback = null;
		
		try {
			IForecastWrapper wrapper = mForecastJsonProvider.parseJson(new ObjectMapper(), 
					                                     mRestRequestCallback.getResponse().parseAsString());
			callback = new ForecastRemoteRequestCallback(wrapper); 
			return callback;
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return  null;
	}

	public IRemoteRequestCallback<Forecast> onGetObservedbyZipCode(String zipCode) {
		// TODO Auto-generated method stub
		return null;
	}

	public IRemoteRequestCallback<ReportingArea> onGetAllReportingAreas() {
		// TODO Auto-generated method stub
		return null;
	}

}
