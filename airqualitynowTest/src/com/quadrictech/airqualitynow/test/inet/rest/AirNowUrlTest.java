package com.quadrictech.airqualitynow.test.inet.rest;

import junit.framework.TestCase;
import roboguice.RoboGuice;
import org.junit.Before;

import android.app.Activity;
import android.content.Context;
import android.test.AndroidTestCase;
import android.test.suitebuilder.annotation.MediumTest;
import android.text.format.Time;

import com.quadrictech.airqualitynow.R;
import com.quadrictech.airqualitynow.inet.rest.AirNowUrl;
import com.quadrictech.airqualitynow.inet.rest.AirNowUrlParameter;

public class AirNowUrlTest extends AndroidTestCase{
	private AirNowUrl mAirNowUrl;
	AirNowUrlParameter mAirNowUrlParameter;
	Context mContext;
	
	@Before
	public void setUp()
	{
		mContext = this.getContext();
	}
	
	@MediumTest
	public void testUrlBuilding(){
		Time now = new Time();
		now.setToNow();
		mAirNowUrlParameter = new AirNowUrlParameter(mContext.getString(R.string.airnowgatewayforecastbyzipcodeurl),
                "78586" , now.format("%Y-%m-%d"), "json", 
                mContext.getString(R.string.airnowgateway_apikey));
		
		mAirNowUrl = new AirNowUrl(mAirNowUrlParameter);
		
		assertEquals("http://ws1.airnowgateway.org/GatewayWebServiceREST/Gateway.svc/forecastbyzipcode?key=secret&zipCode=78586&date=2011-08-16&format=json", mAirNowUrl.url.toString());
	}
}
