package com.devterous.aqn.command;

import java.util.Date;

import com.devterous.aqn.db.callback.IDataRequestCallback;
import com.devterous.aqn.model.viewmodel.ObservationAndForecast;
import com.devterous.aqn.service.IDataProviderService;

public class CommandGetObservationAndForecastByReportingArea extends
		DaoCommand <IDataRequestCallback<ObservationAndForecast>> {
	private int mReportingAreaId;
	private Date mObservationDate;
	
	public CommandGetObservationAndForecastByReportingArea(int reportingAreaId, Date observationDate, IDataProviderService dataProviderService){
		mReportingAreaId = reportingAreaId;
		mObservationDate = observationDate;
		mDataProviderService = dataProviderService;
	}
	
	public IDataRequestCallback<ObservationAndForecast> execute() {
		return this.mDataProviderService.getObservedAndForecastByReportingArea(mReportingAreaId, mObservationDate);
	}

}
