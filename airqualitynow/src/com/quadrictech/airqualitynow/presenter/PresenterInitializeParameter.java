package com.quadrictech.airqualitynow.presenter;

import android.view.View;
import android.widget.ListView;

import com.quadrictech.airqualitynow.view.IObservationView;
import com.quadrictech.airqualitynow.view.IReportingAreaListView;
import com.quadrictech.airqualitynow.view.IForecastView;

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