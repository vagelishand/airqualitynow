package com.quadrictech.airqualitynow.service;

import com.quadrictech.airqualitynow.base.callback.IRequestCallback;
import com.quadrictech.airqualitynow.model.Forecast;
import com.quadrictech.airqualitynow.model.ReportingArea;

public interface IRemoteDataProviderService {
	public IRequestCallback<Forecast> onGetForecastByZipCode(String zipCode);
	public IRequestCallback<Forecast> onGetObservedbyZipCode(String zipCode);
	public IRequestCallback<ReportingArea> onGetAllReportingAreas();
}
