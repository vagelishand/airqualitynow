package com.quadrictech.airqualitynow.service;

import com.quadrictech.airqualitynow.base.callback.IRequestCallback;
import com.quadrictech.airqualitynow.model.Forecast;
import com.quadrictech.airqualitynow.model.ReportingArea;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

public class RemoteDataProviderService extends Service implements IRemoteDataProviderService{

	@Override
	public IBinder onBind(Intent arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	public IRequestCallback<Forecast> onGetForecastByZipCode(String zipCode) {
		// TODO Auto-generated method stub
		return null;
	}

	public IRequestCallback<Forecast> onGetObservedbyZipCode(String zipCode) {
		// TODO Auto-generated method stub
		return null;
	}

	public IRequestCallback<ReportingArea> onGetAllReportingAreas() {
		// TODO Auto-generated method stub
		return null;
	}

}
