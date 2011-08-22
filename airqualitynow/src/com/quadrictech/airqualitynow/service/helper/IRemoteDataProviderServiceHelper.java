package com.quadrictech.airqualitynow.service.helper;

import java.util.List;

import com.quadrictech.airqualitynow.presenter.util.IGuiRunnable;

import android.content.Context;

public interface IRemoteDataProviderServiceHelper extends IServiceHelper {
	public void getForecastByZipCode(String zipCode, IGuiRunnable<?> guiUpdateRunnable);
	public void getObservedByZipCode(String zipCode, IGuiRunnable<?> guiUpdateRunnable);
	public void getReportingAreaByZipCode(String zipCode, List<IGuiRunnable<?>> guiUpdateRunnable);
	public void doBindService(Context context);
	public void doUnBindService();
}
