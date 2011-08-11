package com.quadrictech.airqualitynow.presenter;

import android.widget.ListView;

import com.j256.ormlite.support.ConnectionSource;
import com.quadrictech.airqualitynow.view.IForecastListView;

public interface IForecastListPresenter<T> extends IPresenter<T>{
	public void initialize(IForecastListView<ListView> view, ConnectionSource connectionSource);
	public void initializeList();
	public void onPollutantGuideButtonClick();
	public void onSearchAreaClick();
}
