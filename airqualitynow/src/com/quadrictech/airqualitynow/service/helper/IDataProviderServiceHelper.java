package com.quadrictech.airqualitynow.service.helper;

import com.quadrictech.airqualitynow.presenter.ForecastListPresenter.GuiRunnable;



public interface IDataProviderServiceHelper extends IServiceHelper {
	public void getAllForecasts(GuiRunnable<?> guiUpdateRunnable);
	public void getForecastById(int id, GuiRunnable<?> guiUpdateRunnable);
	public void getAllReportingAreas(GuiRunnable<?> guiUpdateRunnable);
	public void doBindService();
	public void doUnBindService();
}
