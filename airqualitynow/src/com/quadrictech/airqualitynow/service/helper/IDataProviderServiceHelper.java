package com.quadrictech.airqualitynow.service.helper;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import android.content.Context;

import com.quadrictech.airqualitynow.model.Forecast;
import com.quadrictech.airqualitynow.model.Observed;
import com.quadrictech.airqualitynow.model.ReportingArea;
import com.quadrictech.airqualitynow.presenter.util.IGuiRunnable;

public interface IDataProviderServiceHelper extends IServiceHelper {
	public void getAllReportingAreas(IGuiRunnable<?> guiUpdateRunnable);	
	public void getForecastByReportingAreaId(int id, Date issueDate, IGuiRunnable<?> guiUpdateRunnable);
	public void getObservedAndForecastByReportingAreaId(int id, Date issueDate, IGuiRunnable<?> guiUpdateRunnable);
	public void getReportingAreaByZipCode(String zipCode, IGuiRunnable<?> guiUpdateRunnable);
	public ReportingArea insertReportingArea(ReportingArea reportingArea) throws SQLException;
	public void insertReportingArea(ReportingArea reportingArea, IGuiRunnable<?> guiUpdateRunnable);
	public void insertObserved(ReportingArea reportingArea, List<Observed> observedList, IGuiRunnable<?> guiUpdateRunnable);
	public void insertForecast(ReportingArea reportingArea, List<Forecast> forecasts, IGuiRunnable<?> guiUpdateRunnable);
	public void doBindService(Context context);
	public void doUnBindService();
	public void setWindowContext(Context context);
}
