package com.quadrictech.airqualitynow.command;

import com.quadrictech.airqualitynow.db.callback.ILocalRequestCallback;
import com.quadrictech.airqualitynow.service.IDataProviderService;
import com.quadrictech.airqualitynow.model.ReportingArea;

public class CommandGetAllReportingAreas extends DaoCommand<ILocalRequestCallback<ReportingArea>> {
	
	public CommandGetAllReportingAreas(IDataProviderService dataProviderService){
		mDataProviderService = dataProviderService;
	}
	
	public ILocalRequestCallback<ReportingArea> execute() {
		 return mDataProviderService.onGetAllReportingAreas();
	}

}
