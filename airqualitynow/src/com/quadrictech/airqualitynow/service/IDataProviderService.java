package com.quadrictech.airqualitynow.service;

import com.quadrictech.airqualitynow.base.callback.IRequestCallback;
import com.quadrictech.airqualitynow.model.Forecast;
import com.quadrictech.airqualitynow.model.ReportingArea;

public interface IDataProviderService {
	public IRequestCallback<Forecast> onGetAllForecasts();
	public IRequestCallback<Forecast> onGetForecastById(int id);
	public IRequestCallback<ReportingArea> onGetAllReportingAreas();
}
