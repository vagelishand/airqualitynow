package com.quadrictech.airqualitynow.command;

import com.quadrictech.airqualitynow.db.callback.ILocalRequestCallback;
import com.quadrictech.airqualitynow.model.Observed;
import com.quadrictech.airqualitynow.service.IDataProviderService;

public class CommandGetObservedByZipCode extends DaoCommand<ILocalRequestCallback<Observed>>{
	String mZipCode;
	
	public CommandGetObservedByZipCode(String zipCode, IDataProviderService dataProviderService){
		mZipCode = zipCode;
		mDataProviderService = dataProviderService;
	}
	
	public ILocalRequestCallback<Observed> execute() {
		return mDataProviderService.onGetObservedByZipCode(mZipCode);
	}

}
