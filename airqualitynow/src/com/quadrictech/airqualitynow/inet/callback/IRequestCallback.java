package com.quadrictech.airqualitynow.inet.callback;

import com.google.api.client.http.HttpResponse;

/**
 * Interface for updating local db or reporting an error received from remote DB
 * @author Art Gonzalez
 *
 */
public interface IRequestCallback {
	
	public void onError(Throwable exception);
	public void onResponseReceived(HttpResponse response);
	
}
