package com.quadrictech.airqualitynow.db.callback;

import java.util.List;

import com.quadrictech.airqualitynow.base.callback.IRequestCallback;
import com.quadrictech.airqualitynow.model.Forecast;
import com.quadrictech.airqualitynow.model.ForecastWrapper;

public class ForecastRequestCallback implements IRequestCallback<Forecast> {
	private ForecastWrapper mForecastWrapper;
	private Forecast		mForecast;
	private Throwable       mException;
	
	public ForecastRequestCallback(){
		
	}
	
	public ForecastRequestCallback(Forecast forecast){
		mForecast = forecast;
	}
	
	public ForecastRequestCallback(ForecastWrapper forecastWrapper){
		mForecastWrapper = forecastWrapper;
	}
	
	public void onError(Throwable exception) {
		
	}

	public void onResponseReceived(ForecastWrapper response) {
		mForecastWrapper = response;
	}

	public List<Forecast> getList() {
		return mForecastWrapper.getForecast();
	}

	public Forecast getForecast() {
		return mForecast;
	}

	public boolean getErrorStatus() {
		return !(mException == null);
	}

	public String getErrorMessage() {
		return mException.getLocalizedMessage();
	}

	public void onResponseReceived(Forecast response) {
		// TODO Auto-generated method stub
		
	}
}
