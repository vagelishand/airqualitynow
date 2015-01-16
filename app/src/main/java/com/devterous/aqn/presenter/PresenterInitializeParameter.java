package com.devterous.aqn.presenter;

import android.view.View;
import android.widget.ListView;

import com.devterous.aqn.view.IObservationView;
import com.devterous.aqn.view.IReportingAreaListView;
import com.devterous.aqn.view.IForecastView;

public class PresenterInitializeParameter {
	public IReportingAreaListView<ListView> listView;
	public IForecastView<View> forecastView;
	public IObservationView<ListView> observedView;
	public int reportingAreaId;
	public String zipCode;
	
	public PresenterInitializeParameter(IReportingAreaListView<ListView> view) {
		this.listView = view;
	}
	
	public PresenterInitializeParameter(IForecastView<View> view, int reportingAreaId, String zipCode) {
		this.forecastView = view;
		this.reportingAreaId = reportingAreaId;
		this.zipCode = zipCode;
	}
	
	public PresenterInitializeParameter(IObservationView<ListView> observedView, int reportingAreaId, String zipCode){
		this.observedView = observedView;
		this.reportingAreaId = reportingAreaId;
		this.zipCode = zipCode;
	}
}