package com.devterous.aqn.inet.callback;

import java.util.List;

import com.devterous.aqn.model.Forecast;
import com.devterous.aqn.model.ForecastWrapper;
import com.devterous.aqn.model.IForecastWrapper;
import com.devterous.aqn.db.callback.IDataRequestCallback;

public class ForecastRemoteRequestCallback implements IDataRequestCallback<Forecast>{
	IForecastWrapper mForecastWrapper;
	Forecast mForecast;
	Throwable mException;
	
	public ForecastRemoteRequestCallback(){
		mForecastWrapper = new ForecastWrapper();
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

	public void onResponseReceived(List<Forecast> response) {
		mForecastWrapper.setForecast(response);
	}

}
