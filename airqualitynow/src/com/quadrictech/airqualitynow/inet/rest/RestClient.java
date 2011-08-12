package com.quadrictech.airqualitynow.inet.rest;

import java.io.IOException;

import com.google.api.client.googleapis.GoogleUrl;
import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpResponse;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.extensions.android2.AndroidHttp;
import com.quadrictech.airqualitynow.inet.callback.IRequestCallback;

public class RestClient {
	public static final HttpTransport TRANSPORT = (HttpTransport) AndroidHttp.newCompatibleTransport();
	
	public void executeHttpGet(GoogleUrl url, IRequestCallback requestCallback){
		HttpRequest request = null;
		
		try {
			request = TRANSPORT.createRequestFactory().buildGetRequest(url);
			
			HttpResponse response = request.execute();
			
			requestCallback.onResponseReceived(response);
			
		} catch (IOException e) {
			requestCallback.onError(new Throwable("Error getting remote data."));			
		}
		finally{
			try {
				TRANSPORT.shutdown();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
