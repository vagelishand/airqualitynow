package com.quadrictech.airqualitynow.command;

import com.quadrictech.airqualitynow.db.callback.IDataRequestCallback;
import com.quadrictech.airqualitynow.model.Observation;
import com.quadrictech.airqualitynow.service.IRemoteDataProviderService;

public class CommandGetObservationByZipCodeRemote extends RemoteDaoCommand<IDataRequestCallback<Observation>> {
	
	public CommandGetObservationByZipCodeRemote(String zipCode, IRemoteDataProviderService remoteDataProviderService){
		mRemoteDataProviderService = remoteDataProviderService;
	}
	
	public IDataRequestCallback<Observation> execute() {
		return null;
	}

}
