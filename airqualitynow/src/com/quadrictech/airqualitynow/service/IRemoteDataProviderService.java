package com.quadrictech.airqualitynow.service;

import java.io.IOException;
import java.util.List;

import com.quadrictech.airqualitynow.db.callback.IDataRequestCallback;
import com.quadrictech.airqualitynow.inet.callback.RemoteCallbackData;
import com.quadrictech.airqualitynow.model.Forecast;
import com.quadrictech.airqualitynow.model.Observed;

public interface IRemoteDataProviderService {
	public List<Forecast> onGetForecastByZipCode(String zipCode) throws IOException;
	public List<Observed> onGetObservedbyZipCode(String zipCode) throws IOException;
	public IDataRequestCallback<RemoteCallbackData> onGetReportingAreaByZipCode(String zipCode);
}
