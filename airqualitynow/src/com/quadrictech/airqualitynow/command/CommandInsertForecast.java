package com.quadrictech.airqualitynow.command;

import java.util.List;

import com.quadrictech.airqualitynow.db.callback.IDataRequestCallback;
import com.quadrictech.airqualitynow.model.Forecast;
import com.quadrictech.airqualitynow.model.ReportingArea;
import com.quadrictech.airqualitynow.service.IDataProviderService;

public class CommandInsertForecast extends	DaoCommand<IDataRequestCallback<Forecast>> {
	private List<Forecast> mForecasts;
	private ReportingArea mReportingArea;
	
	public CommandInsertForecast(ReportingArea reportingArea, List<Forecast> forecasts, IDataProviderService dataServiceProvider){
		mReportingArea = reportingArea;
		mForecasts = forecasts;
		mDataProviderService = dataServiceProvider;
	}
	
	public IDataRequestCallback<Forecast> execute() {
		return mDataProviderService.insertForecasts(mReportingArea, mForecasts);
	}

}
