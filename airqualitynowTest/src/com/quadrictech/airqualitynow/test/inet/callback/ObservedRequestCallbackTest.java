package com.quadrictech.airqualitynow.test.inet.callback;

import java.util.ArrayList;
import java.util.List;

import roboguice.test.RoboUnitTestCase;

import android.test.suitebuilder.annotation.MediumTest;

import com.quadrictech.airqualitynow.db.callback.ILocalRequestCallback;
import com.quadrictech.airqualitynow.db.callback.ObservedRequestCallback;
import com.quadrictech.airqualitynow.model.Observed;
import com.quadrictech.airqualitynow.robo.AirQualityNowApplication;

public class ObservedRequestCallbackTest extends RoboUnitTestCase<AirQualityNowApplication>{
	private ILocalRequestCallback<Observed> callback;
	
	@Override
	public void setUp()throws Exception{
		super.setUp();
		callback = new ObservedRequestCallback();
	}
	
	@MediumTest
	public void testOnResponseReceived(){
		List<Observed> observed = new ArrayList<Observed>(){
			private static final long serialVersionUID = 1L;
			{
				add(new  Observed());
				add(new  Observed());
			}
		};
		
		callback.onResponseReceived(observed);
		
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
