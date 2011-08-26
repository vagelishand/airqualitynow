package com.quadrictech.airqualitynow.test.json;

import java.io.IOException;

import junit.framework.Assert;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

import roboguice.test.RoboUnitTestCase;

import android.test.suitebuilder.annotation.MediumTest;

import com.quadrictech.airqualitynow.json.IObservationJsonProvider;
import com.quadrictech.airqualitynow.json.ObservationJsonProvider;
import com.quadrictech.airqualitynow.model.IObservationWrapper;
import com.quadrictech.airqualitynow.model.Observation;
import com.quadrictech.airqualitynow.robo.AirQualityNowApplication;

public class ObservedJsonProviderTest extends RoboUnitTestCase<AirQualityNowApplication>{
	private IObservationJsonProvider mJsonProvider;
	private String mJson;
	
	@Override
	public void setUp(){
		mJson = "{\"observed\": [" + 
		"{\"DateObserved\":\"1/15/2010 10:00:00 AM\",\"HourObserved\":\"10\",\"LocalTimeZone\":\"PST\",\"ReportingArea\":\"Napa\",\"StateCode\":\"CA\",\"Latitude\":\"38.3300\",\"Longitude\":\"-122.2800\",\"ParameterName\":\"OZONE\",\"AQI\":\"18\",\"CategoryNumber\":\"1\",\"CategoryName\":\"Good\"}, " + 
		"{\"DateObserved\":\"1/15/2010 12:00:00 AM\",\"HourObserved\":\"0\",\"LocalTimeZone\":\"PST\",\"ReportingArea\":\"Napa\",\"StateCode\":\"CA\",\"Latitude\":\"38.3300\",\"Longitude\":\"-122.2800\",\"ParameterName\":\"PM2.5\",\"AQI\":\"62\",\"CategoryNumber\":\"2\",\"CategoryName\":\"Moderate\"}" + 
		"]}";
		
		mJsonProvider = new ObservationJsonProvider();
	}
	
	@MediumTest
	public void testParseObservedWrapper() throws JsonParseException, JsonMappingException, IOException{
		IObservationWrapper wrapper = mJsonProvider.parseJson(new ObjectMapper(), mJson);
		
		assertNotNull(wrapper);
		assertEquals(2, wrapper.getObserved().size());
	}
	
	@MediumTest
	public void testParseWrapperObserved() throws JsonParseException, JsonMappingException, IOException{
		IObservationWrapper wrapper = mJsonProvider.parseJson(new ObjectMapper(), mJson);
		Observation observed = wrapper.getObserved().get(0);
		
		assertEquals("10", observed.HourObserved);
		assertEquals("Napa", observed.ReportingArea);
		assertEquals("CA", observed.StateCode);
		assertEquals(38.33, observed.Latitude);
		assertEquals(-122.28, observed.Longitude);
		assertEquals("OZONE", observed.ParameterName);
		assertEquals(18, observed.AQI);
		assertEquals(1, observed.CategoryNumber);
		assertEquals("Good", observed.CategoryName);
	}
	
	@MediumTest
	public void testWhenNoWrapperData() throws JsonParseException, IOException{
		mJson = "{\"observed\": \"\"}";
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
