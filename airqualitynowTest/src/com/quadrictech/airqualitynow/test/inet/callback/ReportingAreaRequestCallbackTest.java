package com.quadrictech.airqualitynow.test.inet.callback;

import java.util.ArrayList;
import java.util.List;

import roboguice.test.RoboUnitTestCase;

import android.test.suitebuilder.annotation.MediumTest;

import com.quadrictech.airqualitynow.db.callback.ILocalRequestCallback;
import com.quadrictech.airqualitynow.db.callback.ReportingAreaRequestCallback;
import com.quadrictech.airqualitynow.model.ReportingArea;
import com.quadrictech.airqualitynow.robo.AirQualityNowApplication;

public class ReportingAreaRequestCallbackTest extends RoboUnitTestCase<AirQualityNowApplication>{
	private ILocalRequestCallback<ReportingArea> callback;
	
	@Override
	public void setUp()throws Exception{
		super.setUp();
		callback = new ReportingAreaRequestCallback();
	}
	
	@MediumTest
	public void testOnResponseReceived(){
		List<ReportingArea> reportingArea = new ArrayList<ReportingArea>(){
			private static final long serialVersionUID = 1L;
			{
				add(new  ReportingArea());
				add(new  ReportingArea());
			}
		};
		
		callback.onResponseReceived(reportingArea);
		
		assertNotNull(callback.getList());
		assertEquals(2, callback.getList().size());
	}
	
	@MediumTest
	public void testOnError(){
		Throwable throwable = new Throwable("Test Error");
		callback.onError(throwable);
		
		assertEquals(true, callback.getErrorStatus());
		assertEquals("Test Error", callback.getErrorMessage());
	}
	
	@Override
	public void tearDown()throws Exception{
		callback = null;
		super.tearDown();
	}


}
