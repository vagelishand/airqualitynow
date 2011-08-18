package com.quadrictech.airqualitynow.db.callback;


import java.util.List;

import com.quadrictech.airqualitynow.model.Observed;
import com.quadrictech.airqualitynow.model.ObservedWrapper;

public class ObservedRequestCallback implements ILocalRequestCallback<Observed> {
	public ObservedWrapper wrapper;
	
	public ObservedRequestCallback(ObservedWrapper wrapper){
		this.wrapper = wrapper;
	}
	
	public void onError(Throwable exception) {
		// TODO Auto-generated method stub
		
	}

	public void onResponseReceived(Observed response) {
		// TODO Auto-generated method stub
		
	}

	public List<Observed> getList() {
		return wrapper.getObserved();
	}

	public boolean getErrorStatus() {
		// TODO Auto-generated method stub
		return false;
	}

	public String getErrorMessage() {
		// TODO Auto-generated method stub
		return null;
	}

}
