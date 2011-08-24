package com.quadrictech.airqualitynow.command;

import java.util.Date;

import com.quadrictech.airqualitynow.db.callback.IDataRequestCallback;
import com.quadrictech.airqualitynow.model.Forecast;
import com.quadrictech.airqualitynow.service.IDataProviderService;

public class CommandGetForecastById extends DaoCommand<IDataRequestCallback<Forecast>> {
	private int mForecastId;
	private Date mIssueDate;
	
	public CommandGetForecastById(int forecastId, Date issueDate, IDataProviderService dataProviderService){
		mIssueDate = issueDate;
		mForecastId = forecastId;
		mDataProviderService = dataProviderService;
	}
	
	public IDataRequestCallback<Forecast> execute() {
		return mDataProviderService.onGetForecastByReportingAreaId(mForecastId, mIssueDate);
	}

}
