package com.quadrictech.airqualitynow.command;

import com.quadrictech.airqualitynow.db.callback.ILocalRequestCallback;
import com.quadrictech.airqualitynow.model.Forecast;
import com.quadrictech.airqualitynow.service.IDataProviderService;

public class CommandGetForecastById extends DaoCommand<ILocalRequestCallback<Forecast>> {
	private int mForecastId;
	
	public CommandGetForecastById(int forecastId, IDataProviderService dataProviderService){
		mForecastId = forecastId;
		mDataProviderService = dataProviderService;
	}
	
	public ILocalRequestCallback<Forecast> execute() {
		return mDataProviderService.onGetForecastById(mForecastId);
	}

}
