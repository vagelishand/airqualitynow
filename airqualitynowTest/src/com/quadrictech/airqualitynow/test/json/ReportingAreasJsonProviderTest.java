package com.quadrictech.airqualitynow.test.json;

import org.codehaus.jackson.map.ObjectMapper;

import roboguice.test.RoboUnitTestCase;

import android.test.suitebuilder.annotation.MediumTest;

import com.quadrictech.airqualitynow.json.IReportingAreaJsonProvider;
import com.quadrictech.airqualitynow.json.IReportingAreaWrapper;
import com.quadrictech.airqualitynow.json.ReportingAreaJsonProvider;
import com.quadrictech.airqualitynow.model.ReportingArea;
import com.quadrictech.airqualitynow.robo.AirQualityNowApplication;

public class ReportingAreasJsonProviderTest extends RoboUnitTestCase<AirQualityNowApplication>{
	IReportingAreaJsonProvider mJsonProvider;
	private String mJson;
	
	@Override
	public void setUp(){
		mJson = "{\"reportingArea\": [{\"Name\":\"Aberdeen\",\"State\":\"WA\"},{\"Name\":\"Acadia National Park\",\"State\":\"ME\"},{\"Name\":\"Adams - Mt. Greylock\",\"State\":\"MA\"},{\"Name\":\"Aiken-Augusta - SC/GA\",\"State\":\"SC\"}]}";
		mJsonProvider = new ReportingAreaJsonProvider();
	}
	
	@MediumTest
	public void testParseReportingArea(){
		IReportingAreaWrapper wrapper = mJsonProvider.parseJson(new ObjectMapper(), mJson);
		
		assertNotNull(wrapper);
		assertEquals(4, wrapper.getReportingArea().size());
	}
	
	@MediumTest
	public void testParseWrapperReportingAreas(){
		IReportingAreaWrapper wrapper = mJsonProvider.parseJson(new ObjectMapper(), mJson);
		ReportingArea area = wrapper.getReportingArea().get(0);
		
		assertEquals("Aberdeen", area.Name);
		assertEquals("WA", area.State);
	}
}
