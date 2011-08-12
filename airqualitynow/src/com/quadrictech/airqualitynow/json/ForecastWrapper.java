package com.quadrictech.airqualitynow.json;

import java.util.List;

public class ForecastWrapper {
	public List<com.quadrictech.airqualitynow.model.Forecast> forecasts;
	
	public List<com.quadrictech.airqualitynow.model.Forecast> getForecast(){
		return forecasts;
	}
	
	public void setForecast(List<com.quadrictech.airqualitynow.model.Forecast> forecasts){
		this.forecasts = forecasts;
	}
}
