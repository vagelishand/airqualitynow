package com.quadrictech.airqualitynow.test.forecast;


import java.util.ArrayList;
import java.util.List;

import junit.framework.TestCase;

import android.test.suitebuilder.annotation.MediumTest;
import com.google.android.testing.mocking.UsesMocks;
import com.quadrictech.airqualitynow.model.Forecast;
import com.quadrictech.airqualitynow.presenter.ReportingAreaListPresenter;

public class ForecastListTest extends TestCase{
	
	@UsesMocks(ReportingAreaListPresenter.class)
	@MediumTest
	public void testGetAllReportingAreas(){
	}
	
	public List<Forecast> getForecasts(){
		
		List<Forecast> forecasts = new ArrayList<Forecast>(){
			private static final long serialVersionUID = 1L;

		{
			add(getForecast(10));
			add(getForecast(200));
		}};
		
		return forecasts;
	}
	
	private Forecast getForecast(int aqi){
		Forecast f = new Forecast();
		f.AQI = aqi;
		
		return f;
	}
}
