package com.devterous.aqn.model;

import java.util.List;
import com.devterous.aqn.model.Forecast;

public class ForecastWrapper implements IForecastWrapper{
	public List<Forecast> mForecasts;
	
	public ForecastWrapper(){
		
	}
	
	public List<com.devterous.aqn.model.Forecast> getForecast(){
		return mForecasts;
	}
	
	public void setForecast(List<com.devterous.aqn.model.Forecast> forecasts){
		mForecasts = forecasts;
	}
}
