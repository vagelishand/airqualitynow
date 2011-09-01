package com.quadrictech.airqualitynow.test.model.util;

import java.util.List;

import roboguice.test.RoboUnitTestCase;

import android.test.suitebuilder.annotation.MediumTest;

import com.quadrictech.airqualitynow.model.Forecast;
import com.quadrictech.airqualitynow.model.Pollutant;
import com.quadrictech.airqualitynow.model.util.ForecastUtil;
import com.quadrictech.airqualitynow.robo.AirQualityNowApplication;
import com.quadrictech.airqualitynow.test.db.ForecastDataHelper;
import com.quadrictech.airqualitynow.test.db.PollutantDataHelper;

public class ForecastUtilTest extends RoboUnitTestCase<AirQualityNowApplication>{
	private ForecastUtil mForecastUtil;
	private List<Forecast> mForecasts;
	private List<Pollutant> mPollutants;
	private ForecastDataHelper<Forecast> mForecastDataHelper;
	private PollutantDataHelper mPollutantDataHelper;
	
	@Override
	public void setUp()throws Exception{
		super.setUp();
		mForecastUtil = new ForecastUtil();
		mForecastDataHelper = new ForecastDataHelper<Forecast>(Forecast.class);
		mPollutantDataHelper = new PollutantDataHelper();
		
		mForecasts = mForecastDataHelper.getList();
		mPollutants = mPollutantDataHelper.getList();
	}
	
	@MediumTest
	public void testGetList(){
		List<Forecast> filteredList = mForecastUtil.getFirstTwoForecastRecords(mForecasts, mPollutants);
		
		assertEquals(6, filteredList.size());
	}
	
}
