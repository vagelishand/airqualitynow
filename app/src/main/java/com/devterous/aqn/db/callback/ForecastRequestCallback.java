package com.devterous.aqn.db.callback;

import java.util.List;

import com.devterous.aqn.model.Forecast;
import com.devterous.aqn.model.ForecastWrapper;
import com.devterous.aqn.model.IForecastWrapper;

public class ForecastRequestCallback implements IDataRequestCallback<Forecast> {
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
