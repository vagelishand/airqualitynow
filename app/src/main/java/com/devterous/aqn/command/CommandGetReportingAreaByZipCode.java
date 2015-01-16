package com.devterous.aqn.command;

import com.devterous.aqn.db.callback.IDataRequestCallback;
import com.devterous.aqn.model.ReportingArea;
import com.devterous.aqn.service.IDataProviderService;

public class CommandGetReportingAreaByZipCode extends DaoCommand<IDataRequestCallback<ReportingArea>>  {
	private String mZipCode;
	
	public CommandGetReportingAreaByZipCode(String zipCode, IDataProviderService dataProviderService){
		mZipCode = zipCode;
		mDataProviderService = dataProviderService;
	}
	
	public IDataRequestCallback<ReportingArea> execute() {
		return mDataProviderService.getReportingAreaByZipCode(mZipCode);
	}

}
