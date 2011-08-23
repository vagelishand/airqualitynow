package com.quadrictech.airqualitynow.command;

import com.quadrictech.airqualitynow.db.callback.IDataRequestCallback;
import com.quadrictech.airqualitynow.model.Forecast;
import com.quadrictech.airqualitynow.service.IRemoteDataProviderService;

public class CommandGetForecastByZipCodeRemote extends RemoteDaoCommand<IDataRequestCallback<Forecast>> {
	private String mZipCode;
	
	public CommandGetForecastByZipCodeRemote(String zipCode, IRemoteDataProviderService remoteDataProviderService){
		mZipCode = zipCode;
		mRemoteDataProviderService = remoteDataProviderService;
	}
	
	public IDataRequestCallback<Forecast> execute() {
		return null;
	}

}
