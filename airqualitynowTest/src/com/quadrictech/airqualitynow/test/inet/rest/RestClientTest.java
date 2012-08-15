package com.quadrictech.airqualitynow.test.inet.rest;

import java.io.IOException;

import org.junit.Before;

import junit.framework.TestCase;

import com.google.android.testing.mocking.UsesMocks;

import android.test.suitebuilder.annotation.MediumTest;

import com.quadrictech.airqualitynow.base.callback.IRequestCallback;
import com.quadrictech.airqualitynow.inet.callback.IRestRequestCallback;
import com.quadrictech.airqualitynow.inet.rest.AirNowUrl;
import com.quadrictech.airqualitynow.inet.rest.AirNowUrlParameter;
import com.quadrictech.airqualitynow.inet.rest.RestClient;

public class RestClientTest extends TestCase{
	
	String _zip;
	String _date;
	String _format;
	String _key;
	String _baseUrl;
	
	@Before
	public void setUp()
	{
		_baseUrl = "http://ws1.airnowgateway.org/GatewayWebServiceREST/Gateway.svc/forecastbyzipcode?";
		_zip = "78756";
		_date = "2011-08-11";
		_format = "json";
		_key = "654C8A3C-683A-43FD-BBB2-B1F45B8F1E21";
	}
	
	@MediumTest
	@UsesMocks(IRequestCallback.class)
	public void testExecuteHttpGetWithValidData() throws IOException{
		
		AirNowUrlParameter airNowUrlParam = new AirNowUrlParameter(_baseUrl,
				 _zip, _date, _format, _key);
		
		AirNowUrl airNowUrl = new AirNowUrl(airNowUrlParam);
		
		IRestRequestCallback callback = RestClient.executeHttpGet(airNowUrl);
		assertEquals(false, callback.getErrorStatus());
	}
}
