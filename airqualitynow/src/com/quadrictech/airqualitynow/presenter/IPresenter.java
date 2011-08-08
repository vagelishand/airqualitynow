package com.quadrictech.airqualitynow.presenter;

import com.j256.ormlite.support.ConnectionSource;

public interface IPresenter<T> {
	public void initialize(T view, ConnectionSource connectionSource);
	public void onDestroy();
}
