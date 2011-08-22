package com.quadrictech.airqualitynow.test.service.helper;

import java.util.List;

import roboguice.test.RoboUnitTestCase;

import android.test.suitebuilder.annotation.MediumTest;

import com.google.android.testing.mocking.AndroidMock;
import com.google.android.testing.mocking.UsesMocks;
import com.quadrictech.airqualitynow.db.callback.ILocalRequestCallback;
import com.quadrictech.airqualitynow.model.Forecast;
import com.quadrictech.airqualitynow.presenter.util.IGuiRunnable;
import com.quadrictech.airqualitynow.robo.AirQualityNowApplication;
import com.quadrictech.airqualitynow.service.IDataProviderService;
import com.quadrictech.airqualitynow.service.helper.DataProviderServiceHelper;
import com.quadrictech.airqualitynow.service.helper.IDataProviderServiceHelper;

public class DataProviderServiceHelperTest extends RoboUnitTestCase<AirQualityNowApplication>{
	IDataProviderServiceHelper mDataProviderServiceHelper;
	IDataProviderService mDataProviderService;
	IGuiRunnable<ILocalRequestCallback<Forecast>> mGuiRunnable;
	ILocalRequestCallback<Forecast> mCallback;
	List<Forecast> mForecasts;
	
	@SuppressWarnings("unchecked")
	@Override
	public void setUp(){
		mDataProviderService = AndroidMock.createMock(IDataProviderService.class);
		mGuiRunnable = AndroidMock.createMock(IGuiRunnable.class);
		mDataProviderServiceHelper = new DataProviderServiceHelper(mDataProviderService);
	}
	
	@MediumTest
	@UsesMocks(IDataProviderService.class)
	public void testGetAllForecasts(){
		//AndroidMock.expect(mDataProviderService.onGetAllForecasts()).andReturn(mCallback);
		AndroidMock.replay(mDataProviderService);
		mGuiRunnable.run();
		AndroidMock.replay(mGuiRunnable);
		
		//mDataProviderServiceHelper.getAllForecasts(mGuiRunnable);
		
		AndroidMock.verify(mDataProviderService);
	}
}
