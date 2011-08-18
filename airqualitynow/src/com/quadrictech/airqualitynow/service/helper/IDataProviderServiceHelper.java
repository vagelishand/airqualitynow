package com.quadrictech.airqualitynow.service.helper;

import com.quadrictech.airqualitynow.presenter.util.IGuiRunnable;



public interface IDataProviderServiceHelper extends IServiceHelper {
	public void getAllForecasts(IGuiRunnable<?> guiUpdateRunnable);
	public void getForecastById(int id, IGuiRunnable<?> guiUpdateRunnable);
	public void getAllReportingAreas(IGuiRunnable<?> guiUpdateRunnable);
	public void getObservedByZipCode(String zipCode, IGuiRunnable<?> guiUpdateRunnable);
	public void doBindService();
	public void doUnBindService();
}
