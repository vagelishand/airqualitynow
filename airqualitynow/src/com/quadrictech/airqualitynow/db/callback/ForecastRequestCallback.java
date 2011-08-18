package com.quadrictech.airqualitynow.db.callback;

import java.util.List;

import com.quadrictech.airqualitynow.model.Forecast;
import com.quadrictech.airqualitynow.model.ForecastWrapper;
import com.quadrictech.airqualitynow.model.IForecastWrapper;

public class ForecastRequestCallback implements ILocalRequestCallback<Forecast> {
	private IForecastWrapper mForecastWrapper;
	private Throwable       mException;
	
	public ForecastRequestCallback(){
		mForecastWrapper = new ForecastWrapper();
	}
	
	public void onResponseReceived(List<Forecast> response) {
		mForecastWrapper.setForecast(response);		
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
}
