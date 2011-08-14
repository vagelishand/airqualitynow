package com.quadrictech.airqualitynow.inet.callback;

import java.io.IOException;

import org.codehaus.jackson.map.ObjectMapper;

import com.google.api.client.http.HttpResponse;
import com.google.inject.Inject;
import com.quadrictech.airqualitynow.json.IForecastJsonProvider;
import com.quadrictech.airqualitynow.model.IForecastWrapper;

public class ForecastRESTRequestCallback implements IForecastRESTRequestCallback{
	@Inject private IForecastJsonProvider mJsonProvider;
	@Inject private IForecastWrapper mForecastWrapper;
	
	public ForecastRESTRequestCallback(){
		
	}
	
	public ForecastRESTRequestCallback(IForecastJsonProvider jsonProvider, IForecastWrapper forecastWrapper){
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
