package com.quadrictech.airqualitynow.test.inet.callback;

import roboguice.test.RoboUnitTestCase;

import android.test.suitebuilder.annotation.MediumTest;

import com.google.android.testing.mocking.AndroidMock;
import com.google.android.testing.mocking.UsesMocks;
import com.quadrictech.airqualitynow.inet.callback.IRequestCallback;
import com.quadrictech.airqualitynow.robo.AirQualityNowApplication;

public class ForecastRequestCallbackTest extends RoboUnitTestCase<AirQualityNowApplication>{
	private IRequestCallback callback;
	
	@Override
	public void setUp(){
		callback = AndroidMock.createMock(IRequestCallback.class);
	}
	
	@MediumTest
	@UsesMocks(IRequestCallback.class)
	public void testOnResponseReceived(){
		callback.onResponseReceived(null);
		AndroidMock.replay(callback);
		
		callback.onResponseReceived(null);
		AndroidMock.verify(callback);
	}
	
	@MediumTest
	@UsesMocks(IRequestCallback.class)
	public void testOnError(){
		callback.onError(null);
		AndroidMock.replay(callback);
		
		callback.onError(null);
		AndroidMock.verify(callback);
	}
}
