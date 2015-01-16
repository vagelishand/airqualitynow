package com.devterous.aqn.service;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import com.devterous.aqn.db.IAppRepository;
import com.devterous.aqn.db.callback.IDataRequestCallback;
import com.devterous.aqn.model.Forecast;
import com.devterous.aqn.model.Observation;
import com.devterous.aqn.model.Pollutant;
import com.devterous.aqn.model.ReportingArea;
import com.devterous.aqn.model.viewmodel.ObservationAndForecast;

public interface IDataProviderService {
	public IDataRequestCallback<ReportingArea> getAllReportingAreas();
	public IDataRequestCallback<Observation> getObservedByReportingAreaId(int id, Date date);
	public IDataRequestCallback<Forecast> getForecastsByReportingAreaId(int id, Date issueDate);
	public ReportingArea insertReportArea(ReportingArea reportingArea)throws SQLException;
	public IDataRequestCallback<ReportingArea> insertReportingArea(ReportingArea reportingArea);
	public IDataRequestCallback<ReportingArea> getReportingAreaByZipCode(String zipCode);
	public IDataRequestCallback<Observation> insertObservations(ReportingArea reportingArea, List<Observation> observations);
	public IDataRequestCallback<Forecast> insertForecasts(ReportingArea reportingArea, List<Forecast> forecasts);
	public IDataRequestCallback<ObservationAndForecast> getObservedAndForecastByReportingArea(int id, Date observedDate);
	public Pollutant getPollutantByName(String name)throws SQLException;
	public void updateReportingArea(ReportingArea reportingArea) throws SQLException;
	public IDataRequestCallback<Pollutant> getPollutants();
	public void initialize(IAppRepository appRepository);
}
