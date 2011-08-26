package com.quadrictech.airqualitynow.command;

import java.util.Date;

import com.quadrictech.airqualitynow.db.callback.IDataRequestCallback;
import com.quadrictech.airqualitynow.model.Observation;
import com.quadrictech.airqualitynow.service.IDataProviderService;

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
