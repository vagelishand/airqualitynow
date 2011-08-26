package com.quadrictech.airqualitynow.service.helper;

import com.quadrictech.airqualitynow.command.CommandGetForecastByZipCodeRemote;
import com.quadrictech.airqualitynow.command.CommandGetObservationByZipCodeRemote;
import com.quadrictech.airqualitynow.command.CommandGetReportingAreaByZipCodeRemote;
import com.quadrictech.airqualitynow.command.IDaoCommand;
import com.quadrictech.airqualitynow.db.callback.IDataRequestCallback;
import com.quadrictech.airqualitynow.presenter.util.IGuiRunnable;
import com.quadrictech.airqualitynow.service.RemoteDataProviderService;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.IBinder;

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

	public void getForecastsByZipCode(String zipCode, IGuiRunnable<?> guiUpdateRunnable) {
		task = new RemoteAsyncTask(guiUpdateRunnable);
		task.execute(new CommandGetForecastByZipCodeRemote(zipCode, mRemoteDataProviderService));
	}

	public void getObservationsByZipCode(String zipCode, IGuiRunnable<?> guiUpdateRunnable) {
		task = new RemoteAsyncTask(guiUpdateRunnable);
		task.execute(new CommandGetObservationByZipCodeRemote(zipCode, mRemoteDataProviderService));
	}
	
	public void getReportingAreaByZipCode(String zipCode, IGuiRunnable<?> guiUpdateRunnable) {
		task = new RemoteAsyncTask(guiUpdateRunnable);
		task.execute(new CommandGetReportingAreaByZipCodeRemote(zipCode, mRemoteDataProviderService));
	}

	public void onServiceConnected(ComponentName className, IBinder service) {
		mRemoteDataProviderService = ((RemoteDataProviderService.LocalBinder)service).getService();		
	}

	public void onServiceDisconnected(ComponentName className) {
		
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
		IGuiRunnable<?> mRunnable;
		
		public RemoteAsyncTask(){}
		
		public RemoteAsyncTask(IGuiRunnable<?> runnable){
			mRunnable = runnable;
		}
		
		@Override
		protected IDataRequestCallback<?> doInBackground(IDaoCommand<?>... arg0) {
			callback = (IDataRequestCallback<?>) arg0[0].execute();
			
			if(mRunnable != null){
				mRunnable.setCallback(callback);
				mGuiHandler.post(mRunnable);
			}
			
			return callback;
		}
	}	
}
