package com.quadrictech.airqualitynow.view;

import java.util.List;

import android.view.View;

import com.quadrictech.airqualitynow.model.Forecast;
import com.quadrictech.airqualitynow.presenter.IForecastPresenter;
import com.quadrictech.airqualitynow.presenter.util.ForecastTodayTomorrowArrayAdapter;

public interface IForecastView<T> extends IView<T> {
	public void initialize(IForecastPresenter<View> presenter, String reportingAreaName);
	public void setForecastTableValues(List<Forecast> forecast);
	public void setAdapter(ForecastTodayTomorrowArrayAdapter adapter);
	public void setTodayForecastTableValues(Forecast forecast);
	public void setTomorrowForecastTableValues(Forecast forecast);
}
