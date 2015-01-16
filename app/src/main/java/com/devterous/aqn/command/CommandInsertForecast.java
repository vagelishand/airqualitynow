package com.devterous.aqn.command;

import java.util.List;

import com.devterous.aqn.db.callback.IDataRequestCallback;
import com.devterous.aqn.model.Forecast;
import com.devterous.aqn.model.ReportingArea;
import com.devterous.aqn.service.IDataProviderService;

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
