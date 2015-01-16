package com.devterous.aqn.service;

import java.io.IOException;
import java.util.List;

import com.devterous.aqn.db.callback.IDataRequestCallback;
import com.devterous.aqn.inet.callback.RemoteCallbackData;
import com.devterous.aqn.model.Forecast;
import com.devterous.aqn.model.Observation;

public interface IRemoteDataProviderService {
	public List<Forecast> getForecastsByZipCode(String zipCode) throws IOException;
	public List<Observation> getObservationsbyZipCode(String zipCode) throws IOException;
	public IDataRequestCallback<RemoteCallbackData> onGetReportingAreaByZipCode(String zipCode);
	public void backgroundObservationUpdate();
	public void backgroundForecastUpdate();
}
