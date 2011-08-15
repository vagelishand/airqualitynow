package com.quadrictech.airqualitynow.db.callback;

import java.util.List;

import com.quadrictech.airqualitynow.model.Forecast;
import com.quadrictech.airqualitynow.model.ForecastWrapper;

public class ForecastRequestCallback implements IForecastRequestCallback {
	ForecastWrapper mForecastWrapper;
	
	public void onError(Throwable exception) {
		// TODO Auto-generated method stub

	}

	public void onResponseReceived(ForecastWrapper response) {
		mForecastWrapper = response;
	}

	public List<Forecast> getForecasts() {
		return mForecastWrapper.getForecast();
	}

}
