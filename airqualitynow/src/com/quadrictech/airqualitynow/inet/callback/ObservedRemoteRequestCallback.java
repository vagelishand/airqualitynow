package com.quadrictech.airqualitynow.inet.callback;

import java.util.List;

import com.quadrictech.airqualitynow.model.IObservedWrapper;
import com.quadrictech.airqualitynow.model.Observed;

public class ObservedRemoteRequestCallback implements IRemoteRequestCallback<Observed> {
	IObservedWrapper mWrapper;
	Throwable mException;
	Observed mObserved;
	
	public ObservedRemoteRequestCallback(IObservedWrapper wrapper){
		mWrapper = wrapper;
	}
	
	public void onError(Throwable exception) {
		mException = exception;
	}

	public void onResponseReceived(Observed response) {
		mObserved = response;
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

}
