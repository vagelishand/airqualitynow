package com.devterous.aqn.command;

import java.util.Date;

import com.devterous.aqn.db.callback.IDataRequestCallback;
import com.devterous.aqn.model.Forecast;
import com.devterous.aqn.service.IDataProviderService;

public class CommandGetForecastById extends DaoCommand<IDataRequestCallback<Forecast>> {
	private int mForecastId;
	private Date mIssueDate;
	
	public CommandGetForecastById(int forecastId, Date issueDate, IDataProviderService dataProviderService){
		mIssueDate = issueDate;
		mForecastId = forecastId;
		mDataProviderService = dataProviderService;
	}
	
	public IDataRequestCallback<Forecast> execute() {
		return mDataProviderService.getForecastsByReportingAreaId(mForecastId, mIssueDate);
	}

}
