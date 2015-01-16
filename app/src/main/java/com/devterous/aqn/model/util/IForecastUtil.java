package com.devterous.aqn.model.util;

import java.util.List;

import com.devterous.aqn.model.Forecast;
import com.devterous.aqn.model.Pollutant;

public interface IForecastUtil {
	public List<Forecast> getFirstTwoForecastRecords(List<Forecast> forecasts, List<Pollutant> pollutants);
}
