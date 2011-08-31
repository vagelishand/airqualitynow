package com.quadrictech.airqualitynow.db.callback;

import java.util.List;

import com.quadrictech.airqualitynow.model.Pollutant;

public class PollutantRequestCallback implements IDataRequestCallback<Pollutant> {
	private List<Pollutant> mPollutants;
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

	public void onResponseReceived(List<Pollutant> response) {
		mPollutants = response;
	}

	public List<Pollutant> getList() {
		return mPollutants;
	}

}
