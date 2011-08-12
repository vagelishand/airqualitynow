package com.quadrictech.airqualitynow.test.inet.rest;

import roboguice.test.RoboUnitTestCase;

import com.google.api.client.googleapis.GoogleUrl;
import android.test.suitebuilder.annotation.MediumTest;

import com.quadrictech.airqualitynow.inet.callback.ForecastRequestCallback;
import com.quadrictech.airqualitynow.inet.callback.IRequestCallback;
import com.quadrictech.airqualitynow.inet.rest.RestClient;
import com.quadrictech.airqualitynow.robo.AirQualityNowApplication;

public class RestClientTest extends RoboUnitTestCase<AirQualityNowApplication>{
	private RestClient client;
	
	@Override
	public void setUp(){
		client = new RestClient();
	}
	
	@MediumTest
	public void testExecuteHttpGet(){
		String zip = "78756";
		String date = "2011-08-11";
		String format = "json";
		String key = "654C8A3C-683A-43FD-BBB2-B1F45B8F1E21";
		
		StringBuilder url = new StringBuilder();
		url.append("http://ws1.airnowgateway.org/GatewayWebServiceREST/Gateway.svc/forecastbyzipcode?");
		url.append("zipcode=" + zip);
		url.append("&data=" + date);
		url.append("&format=" + format);
		url.append("&key=" + key);
		
		GoogleUrl googleUrl = new GoogleUrl(url.toString());
		IRequestCallback callback = new ForecastRequestCallback();
		client.executeHttpGet(googleUrl, callback);
	}
}
