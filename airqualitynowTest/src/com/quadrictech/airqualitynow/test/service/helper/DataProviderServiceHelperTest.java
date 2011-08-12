package com.quadrictech.airqualitynow.test.service.helper;

import java.util.List;

import roboguice.test.RoboUnitTestCase;

import android.test.suitebuilder.annotation.MediumTest;

import com.google.android.testing.mocking.AndroidMock;
import com.google.android.testing.mocking.UsesMocks;
import com.quadrictech.airqualitynow.model.Forecast;
import com.quadrictech.airqualitynow.robo.AirQualityNowApplication;
import com.quadrictech.airqualitynow.service.IDataProviderService;
import com.quadrictech.airqualitynow.service.helper.DataProviderServiceHelper;
import com.quadrictech.airqualitynow.service.helper.IDataProviderServiceHelper;

public class DataProviderServiceHelperTest extends RoboUnitTestCase<AirQualityNowApplication>{
	IDataProviderServiceHelper mDataProviderServiceHelper;
	IDataProviderService mDataProviderService;
	List<Forecast> mForecasts;
	
	@Override
	public void setUp(){
		mDataProviderService = AndroidMock.createMock(IDataProviderService.class);
		mDataProviderServiceHelper = new DataProviderServiceHelper();
	}
	
	@MediumTest
	@UsesMocks(IDataProviderService.class)
	public void testGetAllForecasts(){
		mDataProviderService.onGetAllForecasts();
		AndroidMock.replay(mDataProviderService);
		
		mDataProviderServiceHelper.getAllForecasts();
		
		AndroidMock.verify(mDataProviderService);
	}
	
	@MediumTest
	@UsesMocks(IDataProviderService.class)
	public void testGetForecastById(){
		mDataProviderService.onGetForecastById(0);
		AndroidMock.replay(mDataProviderService);
		
		mDataProviderServiceHelper.getForecastById(0);
		AndroidMock.verify(mDataProviderService);
	}
	
	@MediumTest
	@UsesMocks(IDataProviderService.class)
	public void testGetAllReportingAreas(){
		
	}
}
