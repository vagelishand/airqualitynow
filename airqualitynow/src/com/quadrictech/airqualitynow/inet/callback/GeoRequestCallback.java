package com.quadrictech.airqualitynow.inet.callback;

import java.util.List;

import com.quadrictech.airqualitynow.db.callback.IDataRequestCallback;

public class GeoRequestCallback implements IDataRequestCallback<RemoteCallbackData> {
	List<RemoteCallbackData> mRemoteCallbackData;	
	Throwable mException;
	
	public void onError(Throwable exception) {
		mException = exception;
	}

	public boolean getErrorStatus() {
		return !(mException == null);
	}

	public String getErrorMessage() {
		return mException.getLocalizedMessage();
	}

	public void onResponseReceived(List<RemoteCallbackData> response) {
		mRemoteCallbackData = response;		
	}

	public List<RemoteCallbackData> getList() {
		return mRemoteCallbackData;
	}

}
