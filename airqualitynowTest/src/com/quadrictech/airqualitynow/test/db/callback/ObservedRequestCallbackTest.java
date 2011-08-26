package com.quadrictech.airqualitynow.test.db.callback;

import java.util.ArrayList;
import java.util.List;

import roboguice.test.RoboUnitTestCase;

import android.test.suitebuilder.annotation.MediumTest;

import com.quadrictech.airqualitynow.db.callback.IDataRequestCallback;
import com.quadrictech.airqualitynow.db.callback.ObservationRequestCallback;
import com.quadrictech.airqualitynow.model.Observation;
import com.quadrictech.airqualitynow.robo.AirQualityNowApplication;

public class ObservedRequestCallbackTest extends RoboUnitTestCase<AirQualityNowApplication>{
	private IDataRequestCallback<Observation> callback;
	
	@Override
	public void setUp()throws Exception{
		super.setUp();
		callback = new ObservationRequestCallback();
	}
	
	@MediumTest
	public void testOnResponseReceived(){
		List<Observation> observed = new ArrayList<Observation>(){
			private static final long serialVersionUID = 1L;
			{
				add(new  Observation());
				add(new  Observation());
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
