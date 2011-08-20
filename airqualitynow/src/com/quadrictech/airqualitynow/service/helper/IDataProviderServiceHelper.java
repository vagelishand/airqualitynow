package com.quadrictech.airqualitynow.service.helper;

import java.util.List;

import android.content.Context;

import com.quadrictech.airqualitynow.model.Forecast;
import com.quadrictech.airqualitynow.model.Observed;
import com.quadrictech.airqualitynow.model.ReportingArea;
import com.quadrictech.airqualitynow.presenter.util.IGuiRunnable;

public interface IDataProviderServiceHelper extends IServiceHelper {
	public void getAllReportingAreas(IGuiRunnable<?> guiUpdateRunnable);
	public void getForecastById(int id, IGuiRunnable<?> guiUpdateRunnable);
	public void getObservedByZipCode(String zipCode, IGuiRunnable<?> guiUpdateRunnable);
	public void getReportingAreaByZipCode(String zipCode, IGuiRunnable<?> guiUpdateRunnable);
	public void insertReportingArea(ReportingArea reportingArea, IGuiRunnable<?> guiUpdateRunnable);
	public void insertObserved(List<Observed> observedList);
	public void insertForecast(List<Forecast> forecasts);
	public void doBindService(Context context);
	public void doUnBindService();
}
