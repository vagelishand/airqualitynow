package com.quadrictech.airqualitynow.model.util;

import java.util.List;

import com.quadrictech.airqualitynow.model.Forecast;

public interface IForecastUtil {
	public List<Forecast> getFirstTwoForecastRecords(List<Forecast> forecasts, String particle);
}
