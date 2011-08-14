package com.quadrictech.airqualitynow.inet.rest;

import java.io.IOException;

import com.google.api.client.googleapis.GoogleUrl;
import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpResponse;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.extensions.android2.AndroidHttp;
import com.quadrictech.airqualitynow.base.callback.IRequestCallback;

public class RestClient {
	/**
	 * This should return a compatible HttpTransport for current device
	 */
	public static final HttpTransport TRANSPORT = (HttpTransport) AndroidHttp.newCompatibleTransport();
	private boolean mError;
	
	/**
	 * Execute Http Get requests using google apis
	 * 
	 * @param url Callers supply a url string with get parameters
	 * @param requestCallback Uses to process reply/error 
	 */
	public void executeHttpGet(GoogleUrl url, IRequestCallback requestCallback){
		HttpRequest request = null;
		HttpResponse response = null;
		
		try {
			request = TRANSPORT.createRequestFactory().buildGetRequest(url);
			
			response = request.execute();
			
			requestCallback.onResponseReceived(response);
			mError = false;
		} catch (IOException e) {
			requestCallback.onError(new Throwable("Error getting remote data."));
			mError = true;
		}
		finally{
			try {
				if(response != null){
					response = null;
				}
				
				TRANSPORT.shutdown();
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * This is mainly used in the Test project for Unit Testing
	 * @return Request execute error status
	 */
	public boolean getErrorStatus(){
		return mError;
	}
}
