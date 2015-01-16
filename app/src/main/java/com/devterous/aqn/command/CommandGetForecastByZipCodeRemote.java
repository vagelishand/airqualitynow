package com.devterous.aqn.command;

import java.io.IOException;
import java.util.List;

import com.devterous.aqn.db.callback.IDataRequestCallback;
import com.devterous.aqn.inet.callback.ForecastRemoteRequestCallback;
import com.devterous.aqn.model.Forecast;
import com.devterous.aqn.service.IRemoteDataProviderService;

public class CommandGetForecastByZipCodeRemote extends RemoteDaoCommand<IDataRequestCallback<Forecast>> {
	private String mZipCode;
	
	public CommandGetForecastByZipCodeRemote(String zipCode, IRemoteDataProviderService remoteDataProviderService){
		mZipCode = zipCode;
		mRemoteDataProviderService = remoteDataProviderService;
	}
	
	public IDataRequestCallback<Forecast> execute() {
		List<Forecast> forecasts = null;
		IDataRequestCallback<Forecast> callback = new ForecastRemoteRequestCallback();
		
		try{
			forecasts = mRemoteDataProviderService.getForecastsByZipCode(mZipCode);
			callback.onResponseReceived(forecasts);
		}
		catch(IOException e){
			callback.onError(e);
		}
		
		return callback;
	}

}
