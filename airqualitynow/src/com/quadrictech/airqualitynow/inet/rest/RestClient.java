package com.quadrictech.airqualitynow.inet.rest;

import java.io.IOException;

import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpResponse;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.extensions.android2.AndroidHttp;
import com.quadrictech.airqualitynow.inet.callback.IRestRequestCallback;
import com.quadrictech.airqualitynow.inet.callback.RestRequestCallback;

public class RestClient {
	/**
	 * This should return a compatible HttpTransport for current device
	 */
	public static final HttpTransport TRANSPORT = (HttpTransport) AndroidHttp.newCompatibleTransport();
	
	/**
	 * Execute Http Get requests using google apis
	 * @param parameterObject TODO
	 * @param requestCallback Uses to process reply/error 
	 */
	public  synchronized static IRestRequestCallback executeHttpGet(AirNowUrl parameterObject){
		HttpRequest request = null;
		HttpResponse response = null;
		IRestRequestCallback requestCallback = null;
		
		try {
			request = TRANSPORT.createRequestFactory().buildGetRequest(parameterObject.url);
			requestCallback = new RestRequestCallback();
			response = request.execute();
			
			requestCallback.onResponseReceived(response);
		} catch (IOException e) {
			requestCallback.onError(new Throwable("Error getting remote data."));
		}
		
		return requestCallback;
	}
}
