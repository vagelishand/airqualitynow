package com.quadrictech.airqualitynow.test.utils;

import java.text.DateFormat;
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
	    SimpleDateFormat sdf = new SimpleDateFormat("M/dd/yyyy hh:mm:ss aaa");
	    //Date date = sdf.parse("6/10/2010 5:40:30 p.m.");
	    
	    assertEquals("", sdf.format(date));
	}
}
