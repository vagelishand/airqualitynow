package com.devterous.aqn.service.helper;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import android.content.Context;

import com.devterous.aqn.db.callback.IDataRequestCallback;
import com.devterous.aqn.model.Forecast;
import com.devterous.aqn.model.Observation;
import com.devterous.aqn.model.Pollutant;
import com.devterous.aqn.model.ReportingArea;
import com.devterous.aqn.presenter.util.IGuiRunnable;

public interface IDataProviderServiceHelper extends IServiceHelper {
	public void getAllReportingAreas(IGuiRunnable<?> guiUpdateRunnable);	
	public IDataRequestCallback<ReportingArea> getAllReportingAreas();
	public void getForecastsByReportingAreaId(int id, Date issueDate, IGuiRunnable<?> guiUpdateRunnable);
	public void getObservationsByReportingAreaId(int id, Date issueDate, IGuiRunnable<?> guiUpdateRunnable);
	public void getObservationAndForecastByReportingAreaId(int id, Date issueDate, IGuiRunnable<?> guiUpdateRunnable);
	public void getReportingAreaByZipCode(String zipCode, IGuiRunnable<?> guiUpdateRunnable);
	public ReportingArea insertReportingArea(ReportingArea reportingArea) throws SQLException;
	public void insertReportingArea(ReportingArea reportingArea, IGuiRunnable<?> guiUpdateRunnable);
	public void insertObservations(ReportingArea reportingArea, List<Observation> observations, IGuiRunnable<?> guiUpdateRunnable);
	public void insertObservations(ReportingArea reportingArea, List<Observation> observations);
	public void updateReportingArea(ReportingArea reportingArea) throws SQLException;
	public void insertForecasts(ReportingArea reportingArea, List<Forecast> forecasts, IGuiRunnable<?> guiUpdateRunnable);
	public void insertForecasts(ReportingArea reportingArea, List<Forecast> forecasts);
	public void doBindService(Context context);
	public void doUnBindService();
	public void setWindowContext(Context context);
	public List<Pollutant> getAllPollutants();
}
