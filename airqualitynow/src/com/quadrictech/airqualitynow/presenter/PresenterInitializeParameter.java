package com.quadrictech.airqualitynow.presenter;

import android.view.View;
import android.widget.ListView;

import com.quadrictech.airqualitynow.view.IObservedView;
import com.quadrictech.airqualitynow.view.IReportingAreaListView;
import com.quadrictech.airqualitynow.view.IForecastView;

public class PresenterInitializeParameter {
	public IReportingAreaListView<ListView> listView;
	public IForecastView<View> forecastView;
	public IObservedView<View> observedView;
	public int reportingAreaId;
	
	public PresenterInitializeParameter(IReportingAreaListView<ListView> view) {
		this.listView = view;
	}
	
	public PresenterInitializeParameter(IForecastView<View> view, int reportingAreaId) {
		this.forecastView = view;
		this.reportingAreaId = reportingAreaId;
	}
	
	public PresenterInitializeParameter(IObservedView<View> observedView, int reportingAreaId){
		this.observedView = observedView;
		this.reportingAreaId = reportingAreaId;
	}
}