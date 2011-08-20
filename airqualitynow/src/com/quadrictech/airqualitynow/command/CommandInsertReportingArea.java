package com.quadrictech.airqualitynow.command;

import com.quadrictech.airqualitynow.db.callback.ILocalRequestCallback;
import com.quadrictech.airqualitynow.model.ReportingArea;
import com.quadrictech.airqualitynow.service.IDataProviderService;

public class CommandInsertReportingArea extends DaoCommand<ILocalRequestCallback<ReportingArea>> {
	private IDataProviderService mDataProviderService;
	private ReportingArea mReportingArea;
	
	public CommandInsertReportingArea(IDataProviderService dataProviderService, ReportingArea reportingArea){
		mDataProviderService = dataProviderService;
		mReportingArea = reportingArea;
	}
	
	public ILocalRequestCallback<ReportingArea> execute() {
		return mDataProviderService.insertReportingArea(mReportingArea);
	}
}
