package com.quadrictech.airqualitynow.inet.callback;

import com.google.api.client.http.HttpResponse;

public class RestRequestCallback implements IRestRequestCallback {
	public HttpResponse mHttpResponse;
	private Throwable mException;
	
	public void onError(Throwable exception) {
		mException = exception;
	}

	public void onResponseReceived(HttpResponse response) {
		mHttpResponse = response;
	}

	public boolean getErrorStatus() {
		return !(mException == null);
	}

	public String getErrorMessage() {
		return mException.getLocalizedMessage();
	}

	public HttpResponse getResponse() {
		return mHttpResponse;
	}

}
