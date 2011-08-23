package com.quadrictech.airqualitynow.test.db.callback;

import java.util.ArrayList;
import java.util.List;

import roboguice.test.RoboUnitTestCase;

import android.test.suitebuilder.annotation.MediumTest;

import com.quadrictech.airqualitynow.db.callback.ForecastRequestCallback;
import com.quadrictech.airqualitynow.db.callback.IDataRequestCallback;
import com.quadrictech.airqualitynow.model.Forecast;
import com.quadrictech.airqualitynow.robo.AirQualityNowApplication;

public class ForecastRequestCallbackTest extends RoboUnitTestCase<AirQualityNowApplication>{
	private IDataRequestCallback<Forecast> callback;
	
	@Override
	public void setUp()throws Exception{
		super.setUp();
		callback = new ForecastRequestCallback();
	}
	
	@MediumTest
	public void testOnResponseReceived(){
		List<Forecast> forecasts = new ArrayList<Forecast>(){
			private static final long serialVersionUID = 1L;
			{
				add(new  Forecast());
				add(new  Forecast());
			}
		};
		
		callback.onResponseReceived(forecasts);
		
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
