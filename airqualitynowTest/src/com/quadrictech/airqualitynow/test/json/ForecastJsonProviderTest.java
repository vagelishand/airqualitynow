package com.quadrictech.airqualitynow.test.json;

import org.codehaus.jackson.map.ObjectMapper;

import roboguice.test.RoboUnitTestCase;

import android.test.suitebuilder.annotation.MediumTest;

import com.quadrictech.airqualitynow.json.ForecastJsonProvider;
import com.quadrictech.airqualitynow.json.IForecastWrapper;
import com.quadrictech.airqualitynow.model.Forecast;
import com.quadrictech.airqualitynow.robo.AirQualityNowApplication;

public class ForecastJsonProviderTest extends RoboUnitTestCase<AirQualityNowApplication>{
	private ForecastJsonProvider mJsonProvider;
	private String mJson;

	@Override
	public void setUp(){
		mJson = "{\"forecast\": [" +//this is forecast wrapper
		"{\"DateIssue\":\"8/11/2011 12:00:00 AM\",\"DateForecast\":\"8/11/2011 12:00:00 AM\",\"ReportingArea\":\"Napa\",\"StateCode\":\"CA\",\"Latitude\":\"38.3300\",\"Longitude\":\"-122.2800\",\"ParameterName\":\"PM2.5\",\"AQI\":\"42\",\"CategoryNumber\":\"1\",\"CategoryName\":\"Good\",\"ActionDay\":\"False\",\"Discussion\":\"\"}," +
		"{\"DateIssue\":\"8/11/2011 12:00:00 AM\",\"DateForecast\":\"8/12/2011 12:00:00 AM\",\"ReportingArea\":\"Napa\",\"StateCode\":\"CA\",\"Latitude\":\"38.3300\",\"Longitude\":\"-122.2800\",\"ParameterName\":\"PM2.5\",\"AQI\":\"32\",\"CategoryNumber\":\"1\",\"CategoryName\":\"Good\",\"ActionDay\":\"False\",\"Discussion\":\"\"}," +
		"{\"DateIssue\":\"8/11/2011 12:00:00 AM\",\"DateForecast\":\"8/13/2011 12:00:00 AM\",\"ReportingArea\":\"Napa\",\"StateCode\":\"CA\",\"Latitude\":\"38.3300\",\"Longitude\":\"-122.2800\",\"ParameterName\":\"OZONE\",\"AQI\":\"-1\",\"CategoryNumber\":\"1\",\"CategoryName\":\"Good\",\"ActionDay\":\"False\",\"Discussion\":\"\"}," +
		"{\"DateIssue\":\"8/11/2011 12:00:00 AM\",\"DateForecast\":\"8/14/2011 12:00:00 AM\",\"ReportingArea\":\"Napa\",\"StateCode\":\"CA\",\"Latitude\":\"38.3300\",\"Longitude\":\"-122.2800\",\"ParameterName\":\"OZONE\",\"AQI\":\"-1\",\"CategoryNumber\":\"1\",\"CategoryName\":\"Good\",\"ActionDay\":\"False\",\"Discussion\":\"\"}," +
		"{\"DateIssue\":\"8/11/2011 12:00:00 AM\",\"DateForecast\":\"8/15/2011 12:00:00 AM\",\"ReportingArea\":\"Napa\",\"StateCode\":\"CA\",\"Latitude\":\"38.3300\",\"Longitude\":\"-122.2800\",\"ParameterName\":\"OZONE\",\"AQI\":\"-1\",\"CategoryNumber\":\"1\",\"CategoryName\":\"Good\",\"ActionDay\":\"False\",\"Discussion\":\"\"}," +
		"{\"DateIssue\":\"8/11/2011 12:00:00 AM\",\"DateForecast\":\"8/16/2011 12:00:00 AM\",\"ReportingArea\":\"Napa\",\"StateCode\":\"CA\",\"Latitude\":\"38.3300\",\"Longitude\":\"-122.2800\",\"ParameterName\":\"OZONE\",\"AQI\":\"-1\",\"CategoryNumber\":\"1\",\"CategoryName\":\"Good\",\"ActionDay\":\"False\",\"Discussion\":\"\"}" +
		"]}"; 

		mJsonProvider = new ForecastJsonProvider();
	}
	
	@MediumTest
	public void testParseForecastWrapper(){
		IForecastWrapper wrapper = mJsonProvider.parseJson(new ObjectMapper(), mJson);
		
		assertNotNull(wrapper);
		assertEquals(6, wrapper.getForecast().size());
	}
	
	@MediumTest 
	public void testParseWrapperForecasts(){
		IForecastWrapper wrapper = mJsonProvider.parseJson(new ObjectMapper(), mJson);
		Forecast forecast = wrapper.getForecast().get(0);
		
		assertEquals("Napa", forecast.ReportingArea);
		assertEquals("CA", forecast.StateCode);
		assertEquals(38.33, forecast.Latitude);
		assertEquals(-122.28, forecast.Longitude);
		assertEquals("PM2.5", forecast.ParameterName);
		assertEquals(42, forecast.AQI);
		assertEquals(1, forecast.CategoryNumber);
		assertEquals("Good", forecast.CategoryName);
		assertEquals(false, forecast.ActionDay);
	}
	
}
