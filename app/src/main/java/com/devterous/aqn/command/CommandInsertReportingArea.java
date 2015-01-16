package com.devterous.aqn.command;

import com.devterous.aqn.db.callback.IDataRequestCallback;
import com.devterous.aqn.model.ReportingArea;
import com.devterous.aqn.service.IDataProviderService;

public class CommandInsertReportingArea extends DaoCommand<IDataRequestCallback<ReportingArea>>{
	private IDataProviderService mDataProviderService;
	private ReportingArea mReportingArea;
	
	public CommandInsertReportingArea(IDataProviderService dataProviderService, ReportingArea reportingArea){
		mDataProviderService = dataProviderService;
		mReportingArea = reportingArea;
	}
	
	public IDataRequestCallback<ReportingArea> execute() {
		return mDataProviderService.insertReportingArea(mReportingArea);
	}
}
