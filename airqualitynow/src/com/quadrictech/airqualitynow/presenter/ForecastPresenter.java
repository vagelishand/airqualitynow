package com.quadrictech.airqualitynow.presenter;

import java.util.ArrayList;
import java.util.List;

import android.view.View;

import com.quadrictech.airqualitynow.model.Forecast;
import com.quadrictech.airqualitynow.view.IForecastView;

public class ForecastPresenter implements IForecastPresenter<IForecastView<View>> {

	private IForecastView<View> mForecastView;
	
	public void initialize(IForecastView<View> view) {
		mForecastView = view;
		setForecastTableValues();
	}

	public void onDestroy() {
		mForecastView.onDestroy();		
	}
	
	public void setForecastTableValues(){
		List<Forecast> forecasts = new ArrayList<Forecast>();
		Forecast forecast = new Forecast();
		forecast.AQI = 50;
		forecast.Discussion = "test";
		forecasts.add(forecast);
		
		forecast = new Forecast();
		forecast.AQI = 120;
		forecast.Discussion = "another test";
		forecasts.add(forecast);
		
		mForecastView.setForecastTableValues(forecasts);
	}
	
	

}
