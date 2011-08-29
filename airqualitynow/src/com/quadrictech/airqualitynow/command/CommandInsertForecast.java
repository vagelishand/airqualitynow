package com.quadrictech.airqualitynow.command;

import java.util.List;

import com.quadrictech.airqualitynow.db.callback.IDataRequestCallback;
import com.quadrictech.airqualitynow.model.Forecast;
import com.quadrictech.airqualitynow.service.IDataProviderService;

public class CommandInsertForecast extends	DaoCommand<IDataRequestCallback<Forecast>> {
	private List<Forecast> mForecasts;
	
	public CommandInsertForecast(List<Forecast> forecasts, IDataProviderService dataServiceProvider){
		mForecasts = forecasts;
		mDataProviderService = dataServiceProvider;
	}
	
	public IDataRequestCallback<Forecast> execute() {
		return mDataProviderService.insertForecasts(null, mForecasts);
	}

}
