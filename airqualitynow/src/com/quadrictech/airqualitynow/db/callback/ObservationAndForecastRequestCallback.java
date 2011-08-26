package com.quadrictech.airqualitynow.db.callback;

import java.util.List;

import com.quadrictech.airqualitynow.model.viewmodel.ObservedAndForecast;

public class ObservationAndForecastRequestCallback implements	IDataRequestCallback<ObservedAndForecast> {
	private List<ObservedAndForecast> list;
	private Throwable mException;
	
	public void onError(Throwable exception) {
		mException = exception;
	}

	public boolean getErrorStatus() {
		return mException != null;
	}

	public String getErrorMessage() {
		return mException.getLocalizedMessage();
	}

	public void onResponseReceived(List<ObservedAndForecast> response) {
		list = response;
	}

	public List<ObservedAndForecast> getList() {
		return list;
	}

}
