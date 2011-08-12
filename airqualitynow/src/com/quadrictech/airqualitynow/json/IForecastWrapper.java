package com.quadrictech.airqualitynow.json;

import java.util.List;

public interface IForecastWrapper {
	public List<com.quadrictech.airqualitynow.model.Forecast> getForecast();
	public void setForecast(List<com.quadrictech.airqualitynow.model.Forecast> forecasts);
}
