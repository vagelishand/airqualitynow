package com.quadrictech.airqualitynow.service;

import com.quadrictech.airqualitynow.inet.callback.IRemoteRequestCallback;
import com.quadrictech.airqualitynow.model.Forecast;
import com.quadrictech.airqualitynow.model.Observed;

public interface IRemoteDataProviderService {
	public IRemoteRequestCallback<Forecast> onGetForecastByZipCode(String zipCode);
	public IRemoteRequestCallback<Observed> onGetObservedbyZipCode(String zipCode);
}
