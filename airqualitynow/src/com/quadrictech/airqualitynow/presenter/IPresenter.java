package com.quadrictech.airqualitynow.presenter;

public interface IPresenter<T> {
	public void initialize(PresenterInitializeParameter parameterObject);
	public void onDestroy();
}
