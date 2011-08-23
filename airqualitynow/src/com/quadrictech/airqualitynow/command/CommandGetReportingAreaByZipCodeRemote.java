package com.quadrictech.airqualitynow.command;

import com.quadrictech.airqualitynow.db.callback.IDataRequestCallback;
import com.quadrictech.airqualitynow.inet.callback.RemoteCallbackData;
import com.quadrictech.airqualitynow.service.IRemoteDataProviderService;

public class CommandGetReportingAreaByZipCodeRemote extends 
      RemoteDaoCommand<IDataRequestCallback<RemoteCallbackData>> {

	String mZipCode;
	
	public CommandGetReportingAreaByZipCodeRemote(String zipCode, 
			IRemoteDataProviderService remoteDataProviderService){
		mZipCode = zipCode;
		mRemoteDataProviderService = remoteDataProviderService;
	}
	
	public IDataRequestCallback<RemoteCallbackData> execute() {
		return mRemoteDataProviderService.onGetReportingAreaByZipCode(mZipCode);
	}

}
