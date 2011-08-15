package com.quadrictech.airqualitynow.service.helper;

public interface IRemoteDataProviderServiceHelper extends IServiceHelper {
	public void getAllReportingAreas();
	public void getForecastByZipCode(String zipCode);
	public void getObservedByZipCode(String zipCode);
}
