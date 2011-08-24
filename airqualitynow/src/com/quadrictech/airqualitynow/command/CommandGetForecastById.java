package com.quadrictech.airqualitynow.command;

import com.quadrictech.airqualitynow.db.callback.IDataRequestCallback;
import com.quadrictech.airqualitynow.model.Forecast;
import com.quadrictech.airqualitynow.service.IDataProviderService;

public class CommandGetForecastById extends DaoCommand<IDataRequestCallback<Forecast>> {
	private int mForecastId;
	
	public CommandGetForecastById(int forecastId, IDataProviderService dataProviderService){
		mForecastId = forecastId;
		mDataProviderService = dataProviderService;
	}
	
	public IDataRequestCallback<Forecast> execute() {
		return mDataProviderService.onGetForecastByReportingAreaId(mForecastId);
	}

}
