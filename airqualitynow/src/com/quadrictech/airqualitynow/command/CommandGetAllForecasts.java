package com.quadrictech.airqualitynow.command;

import com.quadrictech.airqualitynow.db.callback.ILocalRequestCallback;
import com.quadrictech.airqualitynow.service.IDataProviderService;
import com.quadrictech.airqualitynow.model.Forecast;

public class CommandGetAllForecasts extends DaoCommand<ILocalRequestCallback<Forecast>> {
	
	public CommandGetAllForecasts(IDataProviderService dataProviderService){
		mDataProviderService = dataProviderService;
	}
	
	public ILocalRequestCallback<Forecast> execute() {
		 return mDataProviderService.onGetAllForecasts();
	}

}
