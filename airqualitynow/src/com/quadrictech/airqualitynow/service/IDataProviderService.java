package com.quadrictech.airqualitynow.service;

import com.quadrictech.airqualitynow.db.callback.ILocalRequestCallback;
import com.quadrictech.airqualitynow.model.Forecast;
import com.quadrictech.airqualitynow.model.Observed;
import com.quadrictech.airqualitynow.model.ReportingArea;

public interface IDataProviderService {
	public ILocalRequestCallback<Forecast> onGetAllForecasts();
	public ILocalRequestCallback<Forecast> onGetForecastById(int id);
	public ILocalRequestCallback<ReportingArea> onGetAllReportingAreas();
	public ILocalRequestCallback<Observed> onGetObservedByZipCode(String zipCode);
}
