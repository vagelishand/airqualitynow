package com.quadrictech.airqualitynow.command;

import java.util.Date;

import com.quadrictech.airqualitynow.db.callback.IDataRequestCallback;
import com.quadrictech.airqualitynow.model.Observed;
import com.quadrictech.airqualitynow.service.IDataProviderService;

public class CommandGetObservedById extends DaoCommand<IDataRequestCallback<Observed>>{
	private int mReportingAreaId;
	private Date mObservedDate;
	private IDataProviderService mDataProviderService;
	
	public CommandGetObservedById(int reportingAreaId, Date observedDate, IDataProviderService dataProviderService){
		mReportingAreaId = reportingAreaId;
		mObservedDate = observedDate;
		mDataProviderService = dataProviderService;
	}
	
	public IDataRequestCallback<Observed> execute() {
		return mDataProviderService.getObservedByReportingAreaId(mReportingAreaId, mObservedDate);
	}

}
