package com.quadrictech.airqualitynow.presenter;

import android.view.View;
import android.widget.ListView;

import com.quadrictech.airqualitynow.view.IReportingAreaListView;
import com.quadrictech.airqualitynow.view.IForecastView;

public class PresenterInitializeParameter {
	public IReportingAreaListView<ListView> listView;
	public IForecastView<View> view;
	public int reportingAreaId;
	
	public PresenterInitializeParameter(IReportingAreaListView<ListView> view) {
		this.listView = view;
	}
	
	public PresenterInitializeParameter(IForecastView<View> view, int reportingAreaId) {
		this.view = view;
		this.reportingAreaId = reportingAreaId;
	}
}