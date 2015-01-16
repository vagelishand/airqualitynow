package com.devterous.aqn.presenter;

public interface IPresenter<T> {
	public void initialize(PresenterInitializeParameter parameterObject);
	public void onDestroy();
}
