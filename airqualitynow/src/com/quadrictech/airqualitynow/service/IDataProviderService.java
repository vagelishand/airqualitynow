package com.quadrictech.airqualitynow.service;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import com.quadrictech.airqualitynow.db.IForecastRepository;
import com.quadrictech.airqualitynow.db.IObservationRepository;
import com.quadrictech.airqualitynow.db.IReportingAreaRepository;
import com.quadrictech.airqualitynow.db.callback.IDataRequestCallback;
import com.quadrictech.airqualitynow.model.Forecast;
import com.quadrictech.airqualitynow.model.Observation;
import com.quadrictech.airqualitynow.model.Pollutant;
import com.quadrictech.airqualitynow.model.ReportingArea;
import com.quadrictech.airqualitynow.model.viewmodel.ObservationAndForecast;

public interface IDataProviderService {
	public IDataRequestCallback<ReportingArea> getAllReportingAreas();
	public IDataRequestCallback<Observation> getObservedByReportingAreaId(int id, Date date);
	public IDataRequestCallback<Forecast> getForecastsByReportingAreaId(int id, Date issueDate);
	public void initialize(IForecastRepository fr);
	public void initialize(IReportingAreaRepository rar);
	public void initialize(IObservationRepository or);
	public ReportingArea insertReportArea(ReportingArea reportingArea)throws SQLException;
	public IDataRequestCallback<ReportingArea> insertReportingArea(ReportingArea reportingArea);
	public IDataRequestCallback<ReportingArea> getReportingAreaByZipCode(String zipCode);
	public IDataRequestCallback<Observation> insertObservations(ReportingArea reportingArea, List<Observation> observations);
	public IDataRequestCallback<Forecast> insertForecasts(ReportingArea reportingArea, List<Forecast> forecasts);
	public IDataRequestCallback<ObservationAndForecast> getObservedAndForecastByReportingArea(int id, Date observedDate);
	public Pollutant getPollutantByName(String name)throws SQLException;
	public void updateReportingArea(ReportingArea reportingArea) throws SQLException;
}
