package com.quadrictech.airqualitynow.command;

import com.quadrictech.airqualitynow.db.callback.IDataRequestCallback;
import com.quadrictech.airqualitynow.model.Observed;
import com.quadrictech.airqualitynow.service.IRemoteDataProviderService;

public class CommandGetObservedByZipCodeRemote extends RemoteDaoCommand<IDataRequestCallback<Observed>> {
	private String mZipCode;
	
	public CommandGetObservedByZipCodeRemote(String zipCode, IRemoteDataProviderService remoteDataProviderService){
		mZipCode = zipCode;
		mRemoteDataProviderService = remoteDataProviderService;
	}
	
	public IDataRequestCallback<Observed> execute() {
		return null;
	}

}
