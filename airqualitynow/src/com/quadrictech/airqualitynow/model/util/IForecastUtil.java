package com.quadrictech.airqualitynow.model.util;

import java.util.List;

import com.quadrictech.airqualitynow.model.Forecast;
import com.quadrictech.airqualitynow.model.Pollutant;

public interface IForecastUtil {
	public List<Forecast> getFirstTwoForecastRecords(List<Forecast> forecasts, List<Pollutant> pollutants);
}
