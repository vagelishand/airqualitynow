package com.quadrictech.airqualitynow.test.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import junit.framework.TestCase;

import com.quadrictech.airqualitynow.utils.DateUtil;

import android.test.suitebuilder.annotation.MediumTest;

public class DateUtilsTest extends TestCase{
	@MediumTest
	public void testMMddyyyyFormat() throws ParseException{
		Date date = new Date();
	    //SimpleDateFormat sdf = new SimpleDateFormat("M/dd/yyyy hh:mm:ss aaa");
		SimpleDateFormat sdf = new SimpleDateFormat("M/dd/yyyy");
	    Date newDate = sdf.parse(sdf.format(date));
	    
	    assertEquals("8/13/2012", sdf.format(newDate));
	}
	
	@MediumTest
	public void testGetObservedDate() throws ParseException{
		Date date = DateUtil.getForecastIssueDate();
		SimpleDateFormat sdf = new SimpleDateFormat("M/dd/yyyy");
		
		assertNotNull(date);
		assertEquals("08/13/2012", sdf.format(date));
	}
}
