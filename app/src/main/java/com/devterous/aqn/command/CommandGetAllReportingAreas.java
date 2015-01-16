package com.devterous.aqn.command;

import com.devterous.aqn.db.callback.IDataRequestCallback;
import com.devterous.aqn.service.IDataProviderService;
import com.devterous.aqn.model.ReportingArea;

public class CommandGetAllReportingAreas extends DaoCommand<IDataRequestCallback<ReportingArea>> {
	
	public CommandGetAllReportingAreas(IDataProviderService dataProviderService){
		mDataProviderService = dataProviderService;
	}
	
	public IDataRequestCallback<ReportingArea> execute() {
		 return mDataProviderService.getAllReportingAreas();
	}

}
