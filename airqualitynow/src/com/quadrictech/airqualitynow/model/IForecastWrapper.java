package com.quadrictech.airqualitynow.model;

import java.util.List;


public interface IForecastWrapper {
	public List<Forecast> getForecast();
	public void setForecast(List<Forecast> forecasts);
	public List<Forecast> getObserved();
	public void setObserved(List<Forecast> forecasts);
	
}
