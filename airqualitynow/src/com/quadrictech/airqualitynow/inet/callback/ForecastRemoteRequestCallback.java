package com.quadrictech.airqualitynow.inet.callback;

import java.util.List;

import com.quadrictech.airqualitynow.model.Forecast;
import com.quadrictech.airqualitynow.model.IForecastWrapper;

public class ForecastRemoteRequestCallback implements IRemoteRequestCallback<Forecast> {
	IForecastWrapper mForecastWrapper;
	Forecast mForecast;
	Throwable mException;
	
	public ForecastRemoteRequestCallback(IForecastWrapper forecastWrapper){
		mForecastWrapper = forecastWrapper;
	}
	
	public void onError(Throwable exception) {
		mException = exception;		
	}

	public List<Forecast> getList() {
		return mForecastWrapper.getForecast();
	}

	public boolean getErrorStatus() {
		return !(mException == null);
	}

	public String getErrorMessage() {
		return mException.getLocalizedMessage();
	}

	public void onResponseReceived(Forecast response) {
		mForecast = response;
	}

}
