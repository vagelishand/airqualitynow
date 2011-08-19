package com.quadrictech.airqualitynow.service.helper;

public interface IRemoteDataProviderServiceHelper extends IServiceHelper {
	public void getForecastByZipCode(String zipCode);
	public void getObservedByZipCode(String zipCode);
	public void getReportingAreaByZipCode(String zipCode);
}
