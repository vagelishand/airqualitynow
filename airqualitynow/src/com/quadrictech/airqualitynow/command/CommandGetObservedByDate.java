package com.quadrictech.airqualitynow.command;

import com.quadrictech.airqualitynow.db.callback.ILocalRequestCallback;
import com.quadrictech.airqualitynow.model.Observed;
import com.quadrictech.airqualitynow.service.IDataProviderService;

public class CommandGetObservedByDate extends DaoCommand<ILocalRequestCallback<Observed>>{
	String mZipCode;
	
	public CommandGetObservedByDate(String zipCode, IDataProviderService dataProviderService){
		mZipCode = zipCode;
		mDataProviderService = dataProviderService;
	}
	
	public ILocalRequestCallback<Observed> execute() {
		return mDataProviderService.onGetObservedByDate(null);
	}

}
