package com.quadrictech.airqualitynow.view;

import java.util.List;

import android.view.View;

import com.quadrictech.airqualitynow.model.Forecast;
import com.quadrictech.airqualitynow.model.Observed;
import com.quadrictech.airqualitynow.presenter.IObservedPresenter;

public interface IObservedView<T> extends IView<T> {
	public void initialize(IObservedPresenter<View> presenter, String reportingAreaName);
	public void setObservedTableValues(List<Observed> observedList);
	public void setForecastTableValues(List<Forecast> forecasts);
}
