package com.quadrictech.airqualitynow.db.callback;

import java.util.List;

import com.quadrictech.airqualitynow.model.Forecast;
import com.quadrictech.airqualitynow.model.ForecastWrapper;

public class ForecastRequestCallback implements ILocalRequestCallback<Forecast> {
	private ForecastWrapper mForecastWrapper;
	private Throwable       mException;
	
	public ForecastRequestCallback(){
		
	}
	
	public void onResponseReceived(List<Forecast> response) {
		mForecastWrapper.setForecast(response);		
	}
	
	public void onError(Throwable exception) {
		
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
}
