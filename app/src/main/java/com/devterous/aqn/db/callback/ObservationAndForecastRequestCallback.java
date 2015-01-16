package com.devterous.aqn.db.callback;

import java.util.List;

import com.devterous.aqn.model.viewmodel.ObservationAndForecast;

public class ObservationAndForecastRequestCallback implements	IDataRequestCallback<ObservationAndForecast> {
	private List<ObservationAndForecast> list;
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

	public void onResponseReceived(List<ObservationAndForecast> response) {
		list = response;
	}

	public List<ObservationAndForecast> getList() {
		return list;
	}

}
