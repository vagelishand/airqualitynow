package com.quadrictech.airqualitynow.model;

import java.util.List;
import com.quadrictech.airqualitynow.model.Forecast;

public class ForecastWrapper implements IForecastWrapper{
	public List<Forecast> mForecasts;
	
	public ForecastWrapper(){
		
	}
	
	public List<com.quadrictech.airqualitynow.model.Forecast> getForecast(){
		return mForecasts;
	}
	
	public void setForecast(List<com.quadrictech.airqualitynow.model.Forecast> forecasts){
		mForecasts = forecasts;
	}
}
