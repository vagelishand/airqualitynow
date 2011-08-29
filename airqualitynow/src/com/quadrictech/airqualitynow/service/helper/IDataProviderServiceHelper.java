package com.quadrictech.airqualitynow.service.helper;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import android.content.Context;

import com.quadrictech.airqualitynow.db.callback.IDataRequestCallback;
import com.quadrictech.airqualitynow.model.Forecast;
import com.quadrictech.airqualitynow.model.Observation;
import com.quadrictech.airqualitynow.model.ReportingArea;
import com.quadrictech.airqualitynow.presenter.util.IGuiRunnable;

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
}
