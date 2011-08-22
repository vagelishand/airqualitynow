package com.quadrictech.airqualitynow.service.helper;

import java.util.List;

import com.quadrictech.airqualitynow.command.CommandGetObservedByZipCodeRemote;
import com.quadrictech.airqualitynow.command.IDaoCommand;
import com.quadrictech.airqualitynow.db.callback.IDataRequestCallback;
import com.quadrictech.airqualitynow.model.Forecast;
import com.quadrictech.airqualitynow.model.Observed;
import com.quadrictech.airqualitynow.presenter.util.IGuiRunnable;
import com.quadrictech.airqualitynow.service.RemoteDataProviderService;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.IBinder;
import android.util.Log;

public class RemoteDataProviderServiceHelper implements IRemoteDataProviderServiceHelper, ServiceConnection{
	private RemoteDataProviderService mRemoteDataProviderService;
	private boolean mServiceBound;
	private Context mContext;
	private static RemoteDataProviderServiceHelper mRemoteDataProviderServiceHelper;
	RemoteAsyncTask task;
	IGuiRunnable<?> runnable;
	private final Handler mGuiHandler = new Handler();
	
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
		IDataRequestCallback<Forecast> callback = mRemoteDataProviderService.onGetForecastByZipCode(zipCode);
		List<Forecast>forecasts = callback.getList();
		
		for(Forecast f: forecasts){
			Log.d(this.getClass().getName(), f.ReportingArea);
		}
	}

	public void getObservedByZipCode(final String zipCode) {
		new Thread(new Runnable(){
			public void run(){
				IDataRequestCallback<Observed> callback = mRemoteDataProviderService.onGetObservedbyZipCode(zipCode);
				
				List<Observed>observed = callback.getList();
				
				for(Observed o: observed){
					Log.d(this.getClass().getName(), o.CategoryName);
				}
			}
		}).start();
	}
	
	public void getReportingAreaByZipCode(final String zipCode, final IGuiRunnable<?> guiUpdateRunnable) {
		runnable = guiUpdateRunnable;
		task = new RemoteAsyncTask();
		task.execute(new CommandGetObservedByZipCodeRemote(zipCode, mRemoteDataProviderService));
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
	
	class RemoteAsyncTask extends AsyncTask<IDaoCommand<?>, Integer, IDataRequestCallback<?>>{
		IDataRequestCallback<?> callback;
		@Override
		protected IDataRequestCallback<?> doInBackground(IDaoCommand<?>... arg0) {
			callback = (IDataRequestCallback<?>) arg0[0].execute();
			
			if(runnable != null){
				runnable.setCallback(callback);
				mGuiHandler.post(runnable);
			}
			
			return callback;
		}
	}	
}
