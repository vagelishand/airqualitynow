package com.quadrictech.airqualitynow.test.presenter;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import junit.framework.Assert;

import roboguice.test.RoboUnitTestCase;
import android.test.suitebuilder.annotation.MediumTest;
import android.widget.ListView;

import com.google.android.testing.mocking.AndroidMock;
import com.google.android.testing.mocking.UsesMocks;
import com.quadrictech.airqualitynow.db.IForecastRepository;
import com.quadrictech.airqualitynow.model.Forecast;
import com.quadrictech.airqualitynow.presenter.ForecastListPresenter;
import com.quadrictech.airqualitynow.robo.AirQualityNowApplication;
import com.quadrictech.airqualitynow.view.IForecastListView;

public class ForecastListPresenterTest<T> extends RoboUnitTestCase<AirQualityNowApplication> {
	
	private ForecastListPresenter presenter;
	private IForecastRepository repository;
	private IForecastListView<ListView> view;
	
	@SuppressWarnings("unchecked")
	public void setUp(){
		repository = AndroidMock.createMock(IForecastRepository.class);
				
		view =  AndroidMock.createMock(IForecastListView.class);
		presenter = new ForecastListPresenter(view, repository);
	}
	
	@UsesMocks(IForecastListView.class)
	@MediumTest
	public void testInitializeList() throws SQLException{
		List<Forecast> forecasts = getForecasts();
				
		AndroidMock.expect(repository.queryForAll()).andReturn(forecasts);
		
		AndroidMock.replay(repository);
		
		try {
			presenter.initializeList();
			presenter.asyncTask.get();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		AndroidMock.verify(repository);
	}
	
	@UsesMocks(IForecastListView.class)
	@MediumTest
	public void testOnPollutantButtonClick(){
		try{
			presenter.onPollutantGuideButtonClick();
			AndroidMock.replay(view);
			
			presenter.onPollutantGuideButtonClick();
			AndroidMock.verify(view);
			Assert.fail("Should throw null Context exception");
		}catch(NullPointerException e){
			//
		}
	}
	
	@MediumTest
	public void testOnSearchAreaClick(){
		presenter.onSearchAreaClick();
		AndroidMock.replay(view);
		
		AndroidMock.verify(view);
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
