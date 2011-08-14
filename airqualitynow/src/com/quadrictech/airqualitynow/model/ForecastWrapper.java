package com.quadrictech.airqualitynow.model;

import java.util.List;

public class ForecastWrapper implements IForecastWrapper{
	public List<com.quadrictech.airqualitynow.model.Forecast> forecasts;
	
	public List<com.quadrictech.airqualitynow.model.Forecast> getForecast(){
		return forecasts;
	}
	
	public void setForecast(List<com.quadrictech.airqualitynow.model.Forecast> forecasts){
		this.forecasts = forecasts;
	}
}
