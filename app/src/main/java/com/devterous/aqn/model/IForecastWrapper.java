package com.devterous.aqn.model;

import java.util.List;


public interface IForecastWrapper {
	public List<Forecast> getForecast();
	public void setForecast(List<Forecast> forecasts);
}
