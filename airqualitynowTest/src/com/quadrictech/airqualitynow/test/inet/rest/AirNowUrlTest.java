package com.quadrictech.airqualitynow.test.inet.rest;

import roboguice.test.RoboUnitTestCase;

import android.content.Context;
import android.test.suitebuilder.annotation.MediumTest;
import android.text.format.Time;

import com.quadrictech.airqualitynow.R;
import com.quadrictech.airqualitynow.inet.rest.AirNowUrl;
import com.quadrictech.airqualitynow.inet.rest.AirNowUrlParameter;
import com.quadrictech.airqualitynow.robo.AirQualityNowApplication;

public class AirNowUrlTest extends RoboUnitTestCase<AirQualityNowApplication>{
	private AirNowUrl mAirNowUrl;
	AirNowUrlParameter mAirNowUrlParameter;
	Context mContext;
	
	@Override
	public void setUp(){
		mContext = this.getInstrumentation().getTargetContext();
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
