package com.quadrictech.airqualitynow.command;

import java.util.Date;

import com.quadrictech.airqualitynow.db.callback.IDataRequestCallback;
import com.quadrictech.airqualitynow.model.viewmodel.ObservationAndForecast;
import com.quadrictech.airqualitynow.service.IDataProviderService;

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
