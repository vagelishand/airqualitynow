package com.quadrictech.airqualitynow.presenter;

import roboguice.event.Observes;

import com.quadrictech.airqualitynow.db.callback.ILocalRequestCallback;
import com.quadrictech.airqualitynow.event.BindedToServiceEvent;
import com.quadrictech.airqualitynow.model.Forecast;

public interface IReportingAreaListPresenter<T> extends IPresenter<T>{
	public void initialize(PresenterInitializeParameter parameterObject);
	public void initializeList(@Observes BindedToServiceEvent event);
	public void onPollutantGuideButtonClick();
	public void onSearchAreaClick();
	public void onAddReportingAreaClick();
	public void handleForecasts(ILocalRequestCallback<Forecast> callback);
}
