package com.quadrictech.airqualitynow.test.json;

import java.io.IOException;

import junit.framework.Assert;
import junit.framework.TestCase;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.junit.Before;

import android.test.suitebuilder.annotation.MediumTest;

import com.quadrictech.airqualitynow.json.ForecastJsonProvider;
import com.quadrictech.airqualitynow.model.Forecast;
import com.quadrictech.airqualitynow.model.IForecastWrapper;

public class ForecastJsonProviderTest extends TestCase{
	private ForecastJsonProvider mJsonProvider;
	private String mJson;

	@Before
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
	public void testParseForecastWrapper() throws JsonParseException, JsonMappingException, IOException{
		IForecastWrapper wrapper = mJsonProvider.parseJson(new ObjectMapper(), mJson);
		
		org.junit.Assert.assertNotNull(wrapper);
		org.junit.Assert.assertEquals(6, wrapper.getForecast().size());
	}
	
	@MediumTest 
	public void testParseWrapperForecasts() throws JsonParseException, JsonMappingException, IOException{
		IForecastWrapper wrapper = mJsonProvider.parseJson(new ObjectMapper(), mJson);
		Forecast forecast = wrapper.getForecast().get(0);
		
		org.junit.Assert.assertEquals("Napa", forecast.ReportingArea);
		org.junit.Assert.assertEquals("CA", forecast.StateCode);
		org.junit.Assert.assertEquals(38.33, forecast.Latitude);
		org.junit.Assert.assertEquals(-122.28, forecast.Longitude);
		org.junit.Assert.assertEquals("PM2.5", forecast.ParameterName);
		org.junit.Assert.assertEquals(42, forecast.AQI);
		org.junit.Assert.assertEquals(1, forecast.CategoryNumber);
		org.junit.Assert.assertEquals("Good", forecast.CategoryName);
		org.junit.Assert.assertEquals(false, forecast.ActionDay);
	}
	
	@MediumTest
	public void testWhenNoWrapperData() throws JsonParseException, IOException{
		mJson = "{\"forecast\": \"\"}";
		try{
			mJsonProvider.parseJson(new ObjectMapper(), mJson);
			Assert.fail("Should throw JsonMappingException");
		}
		catch(JsonMappingException e){
			
		}
	}
	
	@MediumTest
	public void testWhenPlainTextReturned() throws JsonMappingException, IOException{
		mJson = "Invalid Key";
		try{
			mJsonProvider.parseJson(new ObjectMapper(), mJson);
			Assert.fail("Should throw JsonParseException");
		}
		catch(JsonParseException e){
			
		}
	}
	
}
