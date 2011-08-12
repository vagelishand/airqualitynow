package com.quadrictech.airqualitynow.inet.callback;

import java.io.IOException;

import org.codehaus.jackson.map.ObjectMapper;

import com.google.api.client.http.HttpResponse;
import com.google.inject.Inject;
import com.quadrictech.airqualitynow.json.IForecastJsonProvider;
import com.quadrictech.airqualitynow.json.IForecastWrapper;

public class ForecastRequestCallback implements IForecastRequestCallback {
	@Inject private IForecastJsonProvider mJsonProvider;
	@Inject private IForecastWrapper mForecastWrapper;
	
	public ForecastRequestCallback(){
		
	}
	
	public ForecastRequestCallback(IForecastJsonProvider jsonProvider, IForecastWrapper forecastWrapper){
		mJsonProvider = jsonProvider;
		mForecastWrapper = forecastWrapper;
	}
	
	public void onError(Throwable exception) {
		exception.printStackTrace();
	}

	public void onResponseReceived(HttpResponse response) {
		try {
			String json = response.parseAsString();
			mForecastWrapper = mJsonProvider.parseJson(new ObjectMapper(), json);
			//TODO save wrapper forecasts to DB
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
