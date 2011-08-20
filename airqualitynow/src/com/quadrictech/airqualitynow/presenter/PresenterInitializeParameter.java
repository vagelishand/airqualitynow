package com.quadrictech.airqualitynow.presenter;

import roboguice.event.EventManager;
import android.view.View;
import android.widget.ListView;

import com.quadrictech.airqualitynow.view.IReportingAreaListView;
import com.quadrictech.airqualitynow.view.IForecastView;

public class PresenterInitializeParameter {
	public IReportingAreaListView<ListView> listView;
	public IForecastView<View> view;
	public EventManager eventManager;

	public PresenterInitializeParameter(IReportingAreaListView<ListView> view,
			EventManager eventManager) {
		this.listView = view;
		this.eventManager = eventManager;
	}
	
	public PresenterInitializeParameter(IForecastView<View> view,
			EventManager eventManager) {
		this.view = view;
		this.eventManager = eventManager;
	}
}