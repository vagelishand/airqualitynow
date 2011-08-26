package com.quadrictech.airqualitynow.service.helper;

import com.quadrictech.airqualitynow.presenter.util.IGuiRunnable;

import android.content.Context;

public interface IRemoteDataProviderServiceHelper extends IServiceHelper {
	public void getForecastsByZipCode(String zipCode, IGuiRunnable<?> guiUpdateRunnable);
	public void getObservationsByZipCode(String zipCode, IGuiRunnable<?> guiUpdateRunnable);
	public void getReportingAreaByZipCode(String zipCode,  IGuiRunnable<?> guiUpdateRunnable);
	public void doBindService(Context context);
	public void doUnBindService();
}
