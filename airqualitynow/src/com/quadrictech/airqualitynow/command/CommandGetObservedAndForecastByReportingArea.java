package com.quadrictech.airqualitynow.command;

import java.util.Date;

import com.quadrictech.airqualitynow.db.callback.IDataRequestCallback;
import com.quadrictech.airqualitynow.model.viewmodel.ObservedAndForecast;
import com.quadrictech.airqualitynow.service.IDataProviderService;

public class CommandGetObservedAndForecastByReportingArea extends
		DaoCommand <IDataRequestCallback<ObservedAndForecast>> {
	private int mReportingAreaId;
	private Date mObservedDate;
	
	public CommandGetObservedAndForecastByReportingArea(int reportingAreaId, Date observedDate, IDataProviderService dataProviderService){
		mReportingAreaId = reportingAreaId;
		mObservedDate = observedDate;
		mDataProviderService = dataProviderService;
	}
	
	public IDataRequestCallback<ObservedAndForecast> execute() {
		return this.mDataProviderService.getObservedAndForecastByReportingArea(mReportingAreaId, mObservedDate);
	}

}
