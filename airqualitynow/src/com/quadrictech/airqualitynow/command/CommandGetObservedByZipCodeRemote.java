package com.quadrictech.airqualitynow.command;

import com.quadrictech.airqualitynow.inet.callback.IRemoteRequestCallback;
import com.quadrictech.airqualitynow.model.Observed;
import com.quadrictech.airqualitynow.service.IRemoteDataProviderService;

public class CommandGetObservedByZipCodeRemote extends RemoteDaoCommand<IRemoteRequestCallback<Observed>> {
	private String mZipCode;
	
	public CommandGetObservedByZipCodeRemote(String zipCode, IRemoteDataProviderService remoteDataProviderService){
		mZipCode = zipCode;
		mRemoteDataProviderService = remoteDataProviderService;
	}
	
	public IRemoteRequestCallback<Observed> execute() {
		return this.mRemoteDataProviderService.onGetObservedbyZipCode(mZipCode);
	}

}
