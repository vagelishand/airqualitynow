package com.quadrictech.airqualitynow.inet.rest;

import java.io.IOException;
import com.google.api.client.http.GenericUrl;
import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.extensions.android2.AndroidHttp;

public class RestClient {
	public static final HttpTransport TRANSPORT = (HttpTransport) AndroidHttp.newCompatibleTransport();
	
	public void executeHttpGet(String url, String acceptValue){
		HttpRequest request = null;
		try {
			request = TRANSPORT.createRequestFactory().buildGetRequest(new GenericUrl(url));
			
			String s = request.execute().parseAsString();
			
			//create callback object to process response
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			//process error in a callback object
			e.printStackTrace();
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
