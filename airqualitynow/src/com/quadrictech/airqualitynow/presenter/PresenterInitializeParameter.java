package com.quadrictech.airqualitynow.presenter;

import roboguice.event.EventManager;
import android.widget.ListView;

import com.j256.ormlite.support.ConnectionSource;
import com.quadrictech.airqualitynow.service.helper.DataProviderServiceHelper;
import com.quadrictech.airqualitynow.view.IForecastListView;

public class PresenterInitializeParameter {
	public IForecastListView<ListView> view;
	public ConnectionSource connectionSource;
	public EventManager eventManager;
	public DataProviderServiceHelper dataProviderServiceHelper;

	public PresenterInitializeParameter(IForecastListView<ListView> view,
			ConnectionSource connectionSource, EventManager eventManager, DataProviderServiceHelper helper) {
		this.view = view;
		this.connectionSource = connectionSource;
		this.eventManager = eventManager;
		this.dataProviderServiceHelper = helper;
	}
}