package com.devterous.aqn.presenter;

import com.devterous.aqn.db.callback.IDataRequestCallback;
import com.devterous.aqn.model.ReportingArea;

public interface IReportingAreaListPresenter<T> extends IPresenter<T>{
	public void initializeList();
	public void onPollutantGuideButtonClick();
	public void onSearchAreaClick();
	public void onAddReportingAreaClick(String zipCode);
	public void handleGetReportingAreas(IDataRequestCallback<ReportingArea> callback);
	public void onViewForecast(ReportingArea reportingArea);
	public void onViewObserved(ReportingArea reportingArea);
}
