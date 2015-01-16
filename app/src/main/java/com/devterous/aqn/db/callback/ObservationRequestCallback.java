package com.devterous.aqn.db.callback;


import java.util.List;

import com.devterous.aqn.model.IObservationWrapper;
import com.devterous.aqn.model.Observation;
import com.devterous.aqn.model.ObservationWrapper;

public class ObservationRequestCallback implements IDataRequestCallback<Observation> {
	private IObservationWrapper mObservedWrapper;
	private Throwable mException;
	
	public ObservationRequestCallback(){
		mObservedWrapper = new ObservationWrapper();
	}
	
	public void onError(Throwable exception) {
		mException = exception;		
	}
	
	public boolean getErrorStatus() {
		return mException != null;
	}
	
	public String getErrorMessage() {
		return mException.getLocalizedMessage();
	}
	
	public void onResponseReceived(List<Observation> response) {
		mObservedWrapper.setObserved(response);
	}
	
	public List<Observation> getList() {
		return mObservedWrapper.getObserved();
	}
	
}
