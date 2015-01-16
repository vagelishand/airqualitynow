package com.devterous.aqn.view;

import java.util.List;

import android.view.View;

import com.devterous.aqn.model.Forecast;
import com.devterous.aqn.presenter.IForecastPresenter;
import com.devterous.aqn.presenter.util.ForecastTodayTomorrowArrayAdapter;

public interface IForecastView<T> extends IView<T> {
	public void initialize(IForecastPresenter<View> presenter, String reportingAreaName);
	public void setForecastTableValues(List<Forecast> forecast);
	public void setAdapter(ForecastTodayTomorrowArrayAdapter adapter);
	public void setTodayForecastTableValues(Forecast forecast);
	public void setTomorrowForecastTableValues(Forecast forecast);
}
