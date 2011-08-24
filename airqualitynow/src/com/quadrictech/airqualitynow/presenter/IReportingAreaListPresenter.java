package com.quadrictech.airqualitynow.presenter;

import com.quadrictech.airqualitynow.db.callback.IDataRequestCallback;
import com.quadrictech.airqualitynow.model.ReportingArea;

public interface IReportingAreaListPresenter<T> extends IPresenter<T>{
	public void initializeList();
	public void onPollutantGuideButtonClick();
	public void onSearchAreaClick();
	public void onAddReportingAreaClick();
	public void handleGetReportingAreas(IDataRequestCallback<ReportingArea> callback);
	public void onViewForecast(ReportingArea reportingArea);
	public void onViewObserved(ReportingArea reportingArea);
}
