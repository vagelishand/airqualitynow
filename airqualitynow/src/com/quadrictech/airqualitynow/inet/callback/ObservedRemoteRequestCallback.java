package com.quadrictech.airqualitynow.inet.callback;

import java.util.List;

import com.quadrictech.airqualitynow.model.IObservedWrapper;
import com.quadrictech.airqualitynow.model.Observed;
import com.quadrictech.airqualitynow.model.ObservedWrapper;

public class ObservedRemoteRequestCallback implements IRemoteRequestCallback<Observed> {
	IObservedWrapper mWrapper;
	Throwable mException;
	Observed mObserved;
	
	public ObservedRemoteRequestCallback(){
		mWrapper = new ObservedWrapper();
	}
	
	public void onError(Throwable exception) {
		mException = exception;
	}

	public List<Observed> getList() {
		return mWrapper.getObserved();
	}

	public boolean getErrorStatus() {
		return !(mException == null);
	}

	public String getErrorMessage() {
		return mException.getLocalizedMessage();
	}

	public void onResponseReceived(List<Observed> response) {
		mWrapper.setObserved(response);
	}
}
