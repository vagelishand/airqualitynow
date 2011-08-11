package com.quadrictech.airqualitynow.presenter;

import android.view.View;

import com.quadrictech.airqualitynow.view.IForecastView;

public interface IForecastPresenter<T> extends IPresenter<T> {
	public void initialize(IForecastView<View> view);
}
