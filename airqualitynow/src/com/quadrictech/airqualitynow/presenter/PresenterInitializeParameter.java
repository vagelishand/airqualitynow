package com.quadrictech.airqualitynow.presenter;

import roboguice.event.EventManager;
import android.view.View;
import android.widget.ListView;

import com.quadrictech.airqualitynow.service.helper.DataProviderServiceHelper;
import com.quadrictech.airqualitynow.service.helper.RemoteDataProviderServiceHelper;
import com.quadrictech.airqualitynow.view.IForecastListView;
import com.quadrictech.airqualitynow.view.IForecastView;

public class PresenterInitializeParameter {
	public IForecastListView<ListView> listView;
	public IForecastView<View> view;
	public EventManager eventManager;
	public DataProviderServiceHelper dataProviderServiceHelper;
	public RemoteDataProviderServiceHelper remoteDataProviderServiceHelper;

	public PresenterInitializeParameter(IForecastListView<ListView> view,
			EventManager eventManager, DataProviderServiceHelper helper) {
		this.listView = view;
		this.eventManager = eventManager;
		this.dataProviderServiceHelper = helper;
	}
	
	public PresenterInitializeParameter(IForecastView<View> view,
			EventManager eventManager, RemoteDataProviderServiceHelper helper) {
		this.view = view;
		this.eventManager = eventManager;
		remoteDataProviderServiceHelper = helper;
	}
}