package com.devterous.aqn.service.helper;

import com.devterous.aqn.presenter.util.IGuiRunnable;

import android.content.Context;

public interface IRemoteDataProviderServiceHelper extends IServiceHelper {
	public void getForecastsByZipCode(String zipCode, IGuiRunnable<?> guiUpdateRunnable);
	public void getObservationsByZipCode(String zipCode, IGuiRunnable<?> guiUpdateRunnable);
	public void getReportingAreaByZipCode(String zipCode,  IGuiRunnable<?> guiUpdateRunnable);
	public void doBindService(Context context);
	public void doUnBindService();
}
