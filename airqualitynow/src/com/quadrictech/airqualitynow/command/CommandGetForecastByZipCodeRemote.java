package com.quadrictech.airqualitynow.command;

import java.io.IOException;
import java.util.List;

import com.quadrictech.airqualitynow.db.callback.IDataRequestCallback;
import com.quadrictech.airqualitynow.inet.callback.ForecastRemoteRequestCallback;
import com.quadrictech.airqualitynow.model.Forecast;
import com.quadrictech.airqualitynow.service.IRemoteDataProviderService;

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
