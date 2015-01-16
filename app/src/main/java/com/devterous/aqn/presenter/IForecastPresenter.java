package com.devterous.aqn.presenter;

import com.devterous.aqn.model.Forecast;

public interface IForecastPresenter<T> extends IPresenter<T> {
	public void onSetTodayForecastTableValues(Forecast forecast);
	public void onSetTomorrowForecastTableValues(Forecast forecast);
}
