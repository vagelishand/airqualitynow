package com.quadrictech.airqualitynow.test.inet.rest;

import roboguice.test.RoboUnitTestCase;

import android.test.suitebuilder.annotation.MediumTest;
import android.util.Log;

import com.quadrictech.airqualitynow.inet.rest.RestClient;
import com.quadrictech.airqualitynow.robo.AirQualityNowApplication;

public class RestClientTest extends RoboUnitTestCase<AirQualityNowApplication>{
	private RestClient client;
	
	@Override
	public void setUp(){
		client = new RestClient();
	}
	
	@MediumTest
	public void testExecuteHttpGet(){//654C8A3C-683A-43FD-BBB2-B1F45B8F1E21
		client.executeHttpGet("http://ws1.airnowgateway.org/GatewayWebServiceREST/Gateway.svc/forecastbyzipcode?zipcode=94954&date=2011-08-11&format=json&key=654C8A3C-683A-43FD-BBB2-B1F45B8F1E21", null);
	}
}
