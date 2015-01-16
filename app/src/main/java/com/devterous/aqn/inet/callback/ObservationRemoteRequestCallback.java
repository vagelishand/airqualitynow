package com.devterous.aqn.inet.callback;

import java.util.List;

import com.devterous.aqn.db.callback.IDataRequestCallback;
import com.devterous.aqn.model.IObservationWrapper;
import com.devterous.aqn.model.Observation;
import com.devterous.aqn.model.ObservationWrapper;

public class ObservationRemoteRequestCallback implements IDataRequestCallback<Observation> {
	IObservationWrapper mWrapper;
	Throwable mException;
	Observation mObserved;
	
	public ObservationRemoteRequestCallback(){
		mWrapper = new ObservationWrapper();
	}
	
	public void onError(Throwable exception) {
		mException = exception;
	}

	public List<Observation> getList() {
		return mWrapper.getObserved();
	}

	public boolean getErrorStatus() {
		return !(mException == null);
	}

	public String getErrorMessage() {
		return mException.getLocalizedMessage();
	}

	public void onResponseReceived(List<Observation> response) {
		mWrapper.setObserved(response);
	}
}
