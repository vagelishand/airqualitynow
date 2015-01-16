package com.devterous.aqn.inet.rest;

import java.io.IOException;

import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpResponse;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.extensions.android2.AndroidHttp;
import com.devterous.aqn.inet.callback.IRestRequestCallback;
import com.devterous.aqn.inet.callback.RestRequestCallback;

public class RestClient {
	/**
	 * This should return a compatible HttpTransport for current device
	 */
	public static final HttpTransport TRANSPORT = (HttpTransport) AndroidHttp.newCompatibleTransport();
	
	/**
	 * Execute Http Get requests using google apis
	 * @param parameterObject TODO
	 * @param requestCallback Uses to process reply/error 
	 * @throws IOException 
	 */
	public  synchronized static IRestRequestCallback executeHttpGet(AirNowUrl parameterObject) throws IOException{
		HttpRequest request = null;
		HttpResponse response = null;
		IRestRequestCallback requestCallback = null;
		
		request = TRANSPORT.createRequestFactory().buildGetRequest(parameterObject.url);
		requestCallback = new RestRequestCallback();
		response = request.execute();
		
		requestCallback.onResponseReceived(response);

		return requestCallback;
	}
}
