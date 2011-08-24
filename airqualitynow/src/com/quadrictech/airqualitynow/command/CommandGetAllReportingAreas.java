package com.quadrictech.airqualitynow.command;

import com.quadrictech.airqualitynow.db.callback.IDataRequestCallback;
import com.quadrictech.airqualitynow.service.IDataProviderService;
import com.quadrictech.airqualitynow.model.ReportingArea;

public class CommandGetAllReportingAreas extends DaoCommand<IDataRequestCallback<ReportingArea>> {
	
	public CommandGetAllReportingAreas(IDataProviderService dataProviderService){
		mDataProviderService = dataProviderService;
	}
	
	public IDataRequestCallback<ReportingArea> execute() {
		 return mDataProviderService.getAllReportingAreas();
	}

}
