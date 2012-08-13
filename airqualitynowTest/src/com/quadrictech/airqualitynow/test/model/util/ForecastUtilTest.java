package com.quadrictech.airqualitynow.test.model.util;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

import org.junit.Before;

import android.test.suitebuilder.annotation.MediumTest;

import com.quadrictech.airqualitynow.model.Forecast;
import com.quadrictech.airqualitynow.model.Pollutant;
import com.quadrictech.airqualitynow.model.util.ForecastUtil;
import com.quadrictech.airqualitynow.test.db.ForecastDataHelper;
import com.quadrictech.airqualitynow.test.db.PollutantDataHelper;
import com.quadrictech.airqualitynow.utils.DateUtil;

public class ForecastUtilTest{
	private ForecastUtil mForecastUtil;
	private List<Forecast> mForecasts;
	private List<Pollutant> mPollutants;
	private ForecastDataHelper<Forecast> mForecastDataHelper;
	private PollutantDataHelper mPollutantDataHelper;
	
	@Before
	public void setUp() throws ParseException{
		
		mForecastUtil = new ForecastUtil();
		mForecastDataHelper = new ForecastDataHelper<Forecast>(Forecast.class);
		mPollutantDataHelper = new PollutantDataHelper();
		
		mForecasts = mForecastDataHelper.getList();
		mPollutants = mPollutantDataHelper.getList();
	}
	
	@MediumTest
	public void testGetList(){
		List<Forecast> filteredList = mForecastUtil.getFirstTwoForecastRecords(mForecasts, mPollutants);
		
		org.junit.Assert.assertEquals(6, filteredList.size());
	}
	
	@MediumTest
	public void testListValuesTwoByTwo() throws ParseException{
		List<Forecast> filteredList = mForecastUtil.getFirstTwoForecastRecords(mForecasts, mPollutants);
		Date aug31 = DateUtil.getDate("8/31/2011", "M/dd/yyyy");
		Date sep01 = DateUtil.getDate("9/1/2011", "M/dd/yyyy");
		
		org.junit.Assert.assertEquals("OZONE", filteredList.get(0).Pollutant.Name);
		org.junit.Assert.assertEquals(aug31, filteredList.get(0).DateForecast);
		org.junit.Assert.assertEquals("OZONE", filteredList.get(1).Pollutant.Name);
		org.junit.Assert.assertEquals(sep01, filteredList.get(1).DateForecast);
		org.junit.Assert.assertEquals("PM10", filteredList.get(2).Pollutant.Name);
		org.junit.Assert.assertEquals(aug31, filteredList.get(2).DateForecast);
		org.junit.Assert.assertEquals("PM10", filteredList.get(3).Pollutant.Name);
		org.junit.Assert.assertEquals(sep01, filteredList.get(3).DateForecast);
		org.junit.Assert.assertEquals("PM2.5", filteredList.get(4).Pollutant.Name);
		org.junit.Assert.assertEquals(aug31, filteredList.get(4).DateForecast);
		org.junit.Assert.assertEquals("PM2.5", filteredList.get(5).Pollutant.Name);
		org.junit.Assert.assertEquals(sep01, filteredList.get(5).DateForecast);
	}
	
	@MediumTest
	public void testPollutantNotNull(){
		List<Forecast> filteredList = mForecastUtil.getFirstTwoForecastRecords(mForecasts, mPollutants);
		
		for(Forecast forecast: filteredList){
			org.junit.Assert.assertNotNull(forecast.Pollutant);
		}
	}
	
	@MediumTest
	public void testSecondDayForecastCreatedWhenOnlyForecastForTodayAvailable() throws ParseException{
		mForecasts = mForecastDataHelper.getListWithOnePollutantPerDate();
		
		List<Forecast> filteredList = mForecastUtil.getFirstTwoForecastRecords(mForecasts, mPollutants);
		
		org.junit.Assert.assertEquals("OZONE", filteredList.get(0).Pollutant.Name);
		org.junit.Assert.assertNotNull(filteredList.get(1).Pollutant);
		org.junit.Assert.assertEquals("OZONE", filteredList.get(1).Pollutant.Name);
	}
	
}
