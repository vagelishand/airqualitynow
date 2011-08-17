package com.quadrictech.airqualitynow.view;

import java.util.List;

import android.view.View;

import com.quadrictech.airqualitynow.model.Forecast;
import com.quadrictech.airqualitynow.model.Observed;
import com.quadrictech.airqualitynow.presenter.IForecastPresenter;

public interface IForecastView<T> extends IView<T> {
	public void initialize(IForecastPresenter<View> presenter);
	public void setForecastTableValues(List<Forecast> forecast);
	public void setObservedTableValues(List<Observed> observedList);
}
