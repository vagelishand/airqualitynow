package com.quadrictech.airqualitynow.presenter;

public interface IForecastListPresenter<T> extends IPresenter<T>{
	public void initialize(PresenterInitializeParameter parameterObject);
	public void initializeList();
	public void onPollutantGuideButtonClick();
	public void onSearchAreaClick();
}
