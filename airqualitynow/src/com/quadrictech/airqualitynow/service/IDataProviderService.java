package com.quadrictech.airqualitynow.service;

import java.util.Date;

import com.quadrictech.airqualitynow.db.IForecastRepository;
import com.quadrictech.airqualitynow.db.IObservedRepository;
import com.quadrictech.airqualitynow.db.IReportingAreaRepository;
import com.quadrictech.airqualitynow.db.callback.IDataRequestCallback;
import com.quadrictech.airqualitynow.model.Forecast;
import com.quadrictech.airqualitynow.model.Observed;
import com.quadrictech.airqualitynow.model.ReportingArea;

public interface IDataProviderService {
	public IDataRequestCallback<ReportingArea> onGetAllReportingAreas();
	public IDataRequestCallback<Observed> onGetObservedByDate(Date date);
	public void initialize(IForecastRepository fr);
	public void initialize(IReportingAreaRepository rar);
	public void initialize(IObservedRepository or);
	public IDataRequestCallback<ReportingArea> insertReportingArea(ReportingArea reportingArea);
	public IDataRequestCallback<ReportingArea> getReportingAreaByZipCode(String zipCode);
}
