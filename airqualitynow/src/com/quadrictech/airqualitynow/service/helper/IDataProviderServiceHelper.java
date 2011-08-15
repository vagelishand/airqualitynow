package com.quadrictech.airqualitynow.service.helper;


public interface IDataProviderServiceHelper extends IServiceHelper {
	public void getAllForecasts();
	public void getForecastById(int id);
	public void getAllReportingAreas();
	public void doBindService();
	public void doUnBindService();
}
