package com.quadrictech.airqualitynow.service.helper;

import java.util.List;

import roboguice.event.EventManager;

import com.quadrictech.airqualitynow.event.BindedToServiceEvent;
import com.quadrictech.airqualitynow.inet.callback.IRemoteRequestCallback;
import com.quadrictech.airqualitynow.model.Forecast;
import com.quadrictech.airqualitynow.service.RemoteDataProviderService;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.util.Log;

public class RemoteDataProviderServiceHelper implements IRemoteDataProviderServiceHelper, ServiceConnection{
	private RemoteDataProviderService mRemoteDataProviderService;
	private boolean mServiceBound;
	private Context mContext;
	private EventManager mEventManager;
	
	public RemoteDataProviderServiceHelper(){
		
	}
	
	public RemoteDataProviderServiceHelper(Context context, EventManager eventManager){
		mContext = context;
		mEventManager = eventManager;
		doBindService();
	}

	public void getAllReportingAreas() {
		// TODO Auto-generated method stub
		
	}

	public void getForecastByZipCode(String zipCode) {
		IRemoteRequestCallback<Forecast> callback = mRemoteDataProviderService.onGetForecastByZipCode(zipCode);
		List<Forecast>forecasts = callback.getList();
		
		for(Forecast f: forecasts){
			Log.d(this.getClass().getName(), f.ReportingArea);
		}
	}

	public void getObservedByZipCode(String zipCode) {
		IRemoteRequestCallback<Forecast> callback = mRemoteDataProviderService.onGetObservedbyZipCode(zipCode);
		List<Forecast>forecasts = callback.getList();
		
		for(Forecast f: forecasts){
			Log.d(this.getClass().getName(), f.ReportingArea);
		}
	}

	public void onServiceConnected(ComponentName className, IBinder service) {
		mRemoteDataProviderService = ((RemoteDataProviderService.LocalBinder)service).getService();		
		mEventManager.fire(mContext, new BindedToServiceEvent(null));
	}

	public void onServiceDisconnected(ComponentName className) {
		// TODO Auto-generated method stub
		
	}
	
	public void doBindService(){
		if(!mServiceBound){
			mServiceBound = mContext.bindService(new Intent(mContext, RemoteDataProviderService.class), this, Context.BIND_AUTO_CREATE);
		}
	}
	
	public void doUnBindService(){
		if(mServiceBound){
			mContext.unbindService(this);
			mServiceBound = false;
		}
	}
}
