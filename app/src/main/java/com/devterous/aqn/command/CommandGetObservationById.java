package com.devterous.aqn.command;

import java.util.Date;

import com.devterous.aqn.db.callback.IDataRequestCallback;
import com.devterous.aqn.model.Observation;
import com.devterous.aqn.service.IDataProviderService;

public class CommandGetObservationById extends DaoCommand<IDataRequestCallback<Observation>>{
	private int mReportingAreaId;
	private Date mObservationDate;
	private IDataProviderService mDataProviderService;
	
	public CommandGetObservationById(int reportingAreaId, Date observationDate, IDataProviderService dataProviderService){
		mReportingAreaId = reportingAreaId;
		mObservationDate = observationDate;
		mDataProviderService = dataProviderService;
	}
	
	public IDataRequestCallback<Observation> execute() {
		return mDataProviderService.getObservedByReportingAreaId(mReportingAreaId, mObservationDate);
	}

}
