package com.quadrictech.airqualitynow.db.callback;


import java.util.List;

import com.quadrictech.airqualitynow.model.IObservedWrapper;
import com.quadrictech.airqualitynow.model.Observed;
import com.quadrictech.airqualitynow.model.ObservedWrapper;

public class ObservedRequestCallback implements ILocalRequestCallback<Observed> {
	private IObservedWrapper mObservedWrapper;
	private Throwable mException;
	
	public ObservedRequestCallback(){
		mObservedWrapper = new ObservedWrapper();
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
	
	public void onResponseReceived(List<Observed> response) {
		mObservedWrapper.setObserved(response);
	}
	
	public List<Observed> getList() {
		return mObservedWrapper.getObserved();
	}
	
}
