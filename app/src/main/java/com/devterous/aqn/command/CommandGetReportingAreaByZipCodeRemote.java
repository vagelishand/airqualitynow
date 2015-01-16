package com.devterous.aqn.command;

import com.devterous.aqn.db.callback.IDataRequestCallback;
import com.devterous.aqn.inet.callback.RemoteCallbackData;
import com.devterous.aqn.service.IRemoteDataProviderService;

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
