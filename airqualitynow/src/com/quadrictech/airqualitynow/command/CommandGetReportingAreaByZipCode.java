package com.quadrictech.airqualitynow.command;

import com.quadrictech.airqualitynow.db.callback.ILocalRequestCallback;
import com.quadrictech.airqualitynow.model.ReportingArea;
import com.quadrictech.airqualitynow.service.IDataProviderService;

public class CommandGetReportingAreaByZipCode extends DaoCommand<ILocalRequestCallback<ReportingArea>>  {
	private String mZipCode;
	
	public CommandGetReportingAreaByZipCode(String zipCode, IDataProviderService dataProviderService){
		mZipCode = zipCode;
		mDataProviderService = dataProviderService;
	}
	
	public ILocalRequestCallback<ReportingArea> execute() {
		return mDataProviderService.getReportingAreaByZipCode(mZipCode);
	}

}
