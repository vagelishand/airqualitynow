package com.quadrictech.airqualitynow.service;

import com.quadrictech.airqualitynow.inet.callback.IRemoteRequestCallback;
import com.quadrictech.airqualitynow.model.Forecast;
import com.quadrictech.airqualitynow.model.ReportingArea;

public interface IRemoteDataProviderService {
	public IRemoteRequestCallback<Forecast> onGetForecastByZipCode(String zipCode);
	public IRemoteRequestCallback<Forecast> onGetObservedbyZipCode(String zipCode);
	public IRemoteRequestCallback<ReportingArea> onGetAllReportingAreas();
}
