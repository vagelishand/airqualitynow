package com.quadrictech.airqualitynow.presenter;

public interface IForecastListPresenter<T> extends IPresenter<T>{
	public void initializeList();
	public void onPollutantGuideButtonClick();
}
