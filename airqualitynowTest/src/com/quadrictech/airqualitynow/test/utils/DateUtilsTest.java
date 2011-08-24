package com.quadrictech.airqualitynow.test.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import roboguice.test.RoboUnitTestCase;

import com.quadrictech.airqualitynow.robo.AirQualityNowApplication;
import com.quadrictech.airqualitynow.utils.DateUtil;

import android.test.suitebuilder.annotation.MediumTest;

public class DateUtilsTest extends RoboUnitTestCase<AirQualityNowApplication>{
	@MediumTest
	public void testMMddyyyyFormat() throws ParseException{
		Date date = new Date();
	    //SimpleDateFormat sdf = new SimpleDateFormat("M/dd/yyyy hh:mm:ss aaa");
		SimpleDateFormat sdf = new SimpleDateFormat("M/dd/yyyy");
	    Date newDate = sdf.parse(sdf.format(date));
	    
	    assertEquals("", sdf.format(newDate));
	}
	
	@MediumTest
	public void testGetObservedDate() throws ParseException{
		Date date = DateUtil.getForecastIssueDate();
		SimpleDateFormat sdf = new SimpleDateFormat("M/dd/yyyy");
		
		assertNotNull(date);
		assertEquals("", sdf.format(date));
	}
}
