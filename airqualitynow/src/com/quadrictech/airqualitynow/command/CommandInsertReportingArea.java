package com.quadrictech.airqualitynow.command;

import com.quadrictech.airqualitynow.db.callback.ILocalRequestCallback;
import com.quadrictech.airqualitynow.model.ReportingArea;
import com.quadrictech.airqualitynow.service.DataProviderService;

public class CommandInsertReportingArea extends DaoCommand<ILocalRequestCallback<ReportingArea>> {
	private DataProviderService mDataProviderService;
	private ReportingArea mReportingArea;
	
	public CommandInsertReportingArea(DataProviderService dataProviderService, ReportingArea reportingArea){
		mDataProviderService = dataProviderService;
		mReportingArea = reportingArea;
	}
	
	public ILocalRequestCallback<ReportingArea> execute() {
		return mDataProviderService.insertReportingArea(mReportingArea);
	}
}
