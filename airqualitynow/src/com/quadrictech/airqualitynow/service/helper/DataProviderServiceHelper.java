package com.quadrictech.airqualitynow.service.helper;

import roboguice.event.EventManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;

import com.quadrictech.airqualitynow.base.IDisposable;
import com.quadrictech.airqualitynow.command.CommandGetAllForecasts;
import com.quadrictech.airqualitynow.command.CommandGetAllReportingAreas;
import com.quadrictech.airqualitynow.command.CommandGetForecastById;
import com.quadrictech.airqualitynow.command.IDaoCommand;
import com.quadrictech.airqualitynow.event.BindedToServiceEvent;
import com.quadrictech.airqualitynow.event.GotAllForecastsEvent;
import com.quadrictech.airqualitynow.service.DataProviderService;

public class DataProviderServiceHelper implements IDataProviderServiceHelper, ServiceConnection, IDisposable{
	private Context mContext;
	private boolean mServiceBound;
	private EventManager mEventManager;
	private static DataProviderServiceHelper mDataProviderServiceHelper;
	private DataProviderService mDataServiceProvider;
	DataAsyncTask task;
	
	public final Handler mHandler = new Handler(){
		@Override
		public void handleMessage(Message m){
			mEventManager.fire(mContext, (GotAllForecastsEvent)m.obj);
		}
	};
	
	public static DataProviderServiceHelper getInstance(){
		if(mDataProviderServiceHelper == null){
			mDataProviderServiceHelper = new DataProviderServiceHelper();
		}
		
		return mDataProviderServiceHelper;
	}
	
	public DataProviderServiceHelper(){
		
	}
	
	public DataProviderServiceHelper(Context context, EventManager eventManager){
		mContext = context;
		mEventManager = eventManager;
		doBindService();
	}
	
	public void getAllForecasts() {
		task = new DataAsyncTask();
		task.execute(new CommandGetAllForecasts(mContext, mDataServiceProvider, mHandler));
	}

	public void getForecastById(int id) {
		task = new DataAsyncTask();
		task.execute(new CommandGetForecastById(id, mContext, mDataServiceProvider, mHandler));
	}

	public void getAllReportingAreas() {
		task = new DataAsyncTask();
		task.execute(new CommandGetAllReportingAreas(mContext, mDataServiceProvider, mHandler));
	}
	
	public void onServiceConnected(ComponentName className, IBinder service) {
		mDataServiceProvider = ((DataProviderService.LocalBinder)service).getService();
		mEventManager.fire(mContext, new BindedToServiceEvent(DataProviderServiceHelper.this));
	}

	public void onServiceDisconnected(ComponentName className) {
		mDataServiceProvider = null;			
	}

	public void doBindService() {
		mServiceBound = mContext.bindService(new Intent(mContext, DataProviderService.class), this, Context.BIND_AUTO_CREATE);
	}

	public void doUnBindService() {
		if(mServiceBound){
			mContext.unbindService(this);
			mServiceBound = false;
		}
	}

	public void onDestroy() {
		doUnBindService();		
	}
	
	class DataAsyncTask extends AsyncTask<IDaoCommand<?>, Integer, Void>{

		@Override
		protected Void doInBackground(IDaoCommand<?>... arg0) {
			arg0[0].execute();
			return null;
		}
	}
}
