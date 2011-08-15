package com.quadrictech.airqualitynow.service;

public interface IDataProviderService {
	public void onGetAllForecasts();
	public void onGetForecastById(int id);
	public void onGetAllReportingAreas();
}
