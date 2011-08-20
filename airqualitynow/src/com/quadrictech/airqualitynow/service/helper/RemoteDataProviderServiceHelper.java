package com.quadrictech.airqualitynow.service.helper;

import java.util.List;

import com.quadrictech.airqualitynow.event.ObservedDataRetrieved;
import com.quadrictech.airqualitynow.inet.callback.IRemoteRequestCallback;
import com.quadrictech.airqualitynow.model.Forecast;
import com.quadrictech.airqualitynow.model.Observed;
import com.quadrictech.airqualitynow.model.ReportingArea;
import com.quadrictech.airqualitynow.presenter.util.IGuiRunnable;
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
	private static RemoteDataProviderServiceHelper mRemoteDataProviderServiceHelper;
	//TODO implement AsyncTask for remote calls
	public RemoteDataProviderServiceHelper(){
		
	}
	
	public static RemoteDataProviderServiceHelper getInstance(){
		if(mRemoteDataProviderServiceHelper == null){
			mRemoteDataProviderServiceHelper = new RemoteDataProviderServiceHelper();
		}
		return mRemoteDataProviderServiceHelper;
	}

	public void getForecastByZipCode(String zipCode) {
		IRemoteRequestCallback<Forecast> callback = mRemoteDataProviderService.onGetForecastByZipCode(zipCode);
		List<Forecast>forecasts = callback.getList();
		
		for(Forecast f: forecasts){
			Log.d(this.getClass().getName(), f.ReportingArea);
		}
	}

	public void getObservedByZipCode(final String zipCode) {
		new Thread(new Runnable(){
			public void run(){
				IRemoteRequestCallback<Observed> callback = mRemoteDataProviderService.onGetObservedbyZipCode(zipCode);
				ObservedDataRetrieved retrieved = new ObservedDataRetrieved();
				retrieved.mRemoteRequestCallback = callback;
				
				List<Observed>observed = callback.getList();
				
				for(Observed o: observed){
					Log.d(this.getClass().getName(), o.CategoryName);
				}
			}
		}).start();
	}
	
	public void getReportingAreaByZipCode(final String zipCode, final IGuiRunnable<?> guiUpdateRunnable) {
		new Thread(new Runnable(){
			public void run(){
				IRemoteRequestCallback<Observed> callback = mRemoteDataProviderService.onGetObservedbyZipCode(zipCode);
				
				if(!callback.getErrorStatus() && callback.getList().size() > 0){
					Observed observed = callback.getList().get(0);
					ReportingArea area = new ReportingArea();
					area.Name = observed.ReportingArea;
					area.State = observed.StateCode;
					area.ObservedAQI = observed.AQI;
					
					DataProviderServiceHelper.getInstance().insertReportingArea(area, guiUpdateRunnable);
				}
				
			}
		}).start();
		
	}

	public void onServiceConnected(ComponentName className, IBinder service) {
		mRemoteDataProviderService = ((RemoteDataProviderService.LocalBinder)service).getService();		
	}

	public void onServiceDisconnected(ComponentName className) {
		// TODO Auto-generated method stub
		
	}
	
	public void doBindService(Context context){
		mContext = context;
		
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
