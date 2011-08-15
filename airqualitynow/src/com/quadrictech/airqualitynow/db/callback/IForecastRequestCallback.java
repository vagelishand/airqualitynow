package com.quadrictech.airqualitynow.db.callback;

import java.util.List;

import com.quadrictech.airqualitynow.base.callback.IRequestCallback;
import com.quadrictech.airqualitynow.model.Forecast;
import com.quadrictech.airqualitynow.model.ForecastWrapper;

public interface IForecastRequestCallback extends IRequestCallback<ForecastWrapper> {
	public List<Forecast> getForecasts();
}
