package com.quadrictech.airqualitynow.service;

import com.quadrictech.airqualitynow.db.callback.IDataRequestCallback;
import com.quadrictech.airqualitynow.model.Forecast;
import com.quadrictech.airqualitynow.model.Observed;

public interface IRemoteDataProviderService {
	public IDataRequestCallback<Forecast> onGetForecastByZipCode(String zipCode);
	public IDataRequestCallback<Observed> onGetObservedbyZipCode(String zipCode);
}
