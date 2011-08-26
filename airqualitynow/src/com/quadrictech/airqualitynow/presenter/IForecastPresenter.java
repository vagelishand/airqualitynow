package com.quadrictech.airqualitynow.presenter;

import com.quadrictech.airqualitynow.model.Forecast;

public interface IForecastPresenter<T> extends IPresenter<T> {
	public void onSetTodayForecastTableValues(Forecast forecast);
	public void onSetTomorrowForecastTableValues(Forecast forecast);
}
