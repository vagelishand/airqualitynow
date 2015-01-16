package com.devterous.aqn.command;

import java.io.IOException;
import java.util.List;

import com.devterous.aqn.db.callback.IDataRequestCallback;
import com.devterous.aqn.db.callback.ObservationRequestCallback;
import com.devterous.aqn.model.Observation;
import com.devterous.aqn.service.IRemoteDataProviderService;

public class CommandGetObservationByZipCodeRemote extends RemoteDaoCommand<IDataRequestCallback<Observation>> {
	
	private String mZipCode;

	public CommandGetObservationByZipCodeRemote(String zipCode, IRemoteDataProviderService remoteDataProviderService){
		mRemoteDataProviderService = remoteDataProviderService;
		mZipCode = zipCode;
	}
	
	public IDataRequestCallback<Observation> execute() {
		List<Observation> list = null;
		IDataRequestCallback<Observation> callback = new ObservationRequestCallback();
		
		try {
			list = mRemoteDataProviderService.getObservationsbyZipCode(mZipCode);
			
			callback.onResponseReceived(list);
			
		} catch (IOException e) {
			callback.onError(e);
		}
		
		return callback;
	}

}
