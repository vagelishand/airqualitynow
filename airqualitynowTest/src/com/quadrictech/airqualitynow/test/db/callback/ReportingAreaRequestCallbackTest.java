package com.quadrictech.airqualitynow.test.db.callback;

import java.util.ArrayList;
import java.util.List;

import junit.framework.TestCase;

import org.junit.After;
import org.junit.Before;

import android.test.suitebuilder.annotation.MediumTest;

import com.quadrictech.airqualitynow.db.callback.IDataRequestCallback;
import com.quadrictech.airqualitynow.db.callback.ReportingAreaRequestCallback;
import com.quadrictech.airqualitynow.model.ReportingArea;

public class ReportingAreaRequestCallbackTest extends TestCase{
	private IDataRequestCallback<ReportingArea> callback;
	
	@Before
	public void setUp()
	{
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
		
		org.junit.Assert.assertNotNull(callback.getList());
		org.junit.Assert.assertEquals(2, callback.getList().size());
	}
	
	@MediumTest
	public void testOnError(){
		Throwable throwable = new Throwable("Test Error");
		callback.onError(throwable);
		
		org.junit.Assert.assertEquals(true, callback.getErrorStatus());
		org.junit.Assert.assertEquals("Test Error", callback.getErrorMessage());
	}
	
	@After
	public void tearDown(){
		callback = null;
	}


}
