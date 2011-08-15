package com.quadrictech.airqualitynow.presenter;

import roboguice.event.Observes;

import com.quadrictech.airqualitynow.event.BindedToServiceEvent;

public interface IForecastListPresenter<T> extends IPresenter<T>{
	public void initialize(PresenterInitializeParameter parameterObject);
	public void initializeList(@Observes BindedToServiceEvent event);
	public void onPollutantGuideButtonClick();
	public void onSearchAreaClick();
}
