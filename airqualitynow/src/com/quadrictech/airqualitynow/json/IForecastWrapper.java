package com.quadrictech.airqualitynow.json;

import java.util.List;

import com.quadrictech.airqualitynow.model.Forecast;

public interface IForecastWrapper {
	public List<Forecast> getForecast();
	public void setForecast(List<Forecast> forecasts);
}
