package com.quadrictech.airqualitynow.service.helper;

import java.util.List;

import roboguice.event.EventManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.IBinder;

import com.quadrictech.airqualitynow.base.IDisposable;
import com.quadrictech.airqualitynow.command.CommandGetAllReportingAreas;
import com.quadrictech.airqualitynow.command.CommandGetForecastById;
import com.quadrictech.airqualitynow.command.CommandInsertReportingArea;
import com.quadrictech.airqualitynow.command.IDaoCommand;
import com.quadrictech.airqualitynow.db.callback.ILocalRequestCallback;
import com.quadrictech.airqualitynow.event.BindedToServiceEvent;
import com.quadrictech.airqualitynow.model.Forecast;
import com.quadrictech.airqualitynow.model.Observed;
import com.quadrictech.airqualitynow.model.ReportingArea;
import com.quadrictech.airqualitynow.presenter.util.IGuiRunnable;
import com.quadrictech.airqualitynow.service.DataProviderService;
import com.quadrictech.airqualitynow.service.IDataProviderService;

public class DataProviderServiceHelper implements IDataProviderServiceHelper, ServiceConnection, IDisposable{
	private Context mContext;
	private boolean mServiceBound;
	private EventManager mEventManager;
	private static DataProviderServiceHelper mDataProviderServiceHelper;
	private IDataProviderService mDataServiceProvider;
	DataAsyncTask<?> task;
	IGuiRunnable<?> runnable;
	public final Handler mGuiHandler = new Handler();
	
	public static DataProviderServiceHelper getInstance(){
		if(mDataProviderServiceHelper == null){
			mDataProviderServiceHelper = new DataProviderServiceHelper();
		}
		
		return mDataProviderServiceHelper;
	}
	
	public DataProviderServiceHelper(){
		
	}
	
	public DataProviderServiceHelper(IDataProviderService dataProviderService){
		mDataServiceProvider = dataProviderService;
	}
	
	public DataProviderServiceHelper(Context context, EventManager eventManager){
		mContext = context;
		mEventManager = eventManager;
		doBindService();
	}

	public void getAllReportingAreas(IGuiRunnable<?> guiUpdateRunnable) {
		runnable = guiUpdateRunnable;
		task = new DataAsyncTask<ILocalRequestCallback<ReportingArea>>();
		task.execute(new CommandGetAllReportingAreas(mDataServiceProvider));		
	}
	
	public void getForecastById(int id, IGuiRunnable<?> guiUpdateRunnable) {
		runnable = guiUpdateRunnable;
		task = new DataAsyncTask<ILocalRequestCallback<Forecast>>();
		task.execute(new CommandGetForecastById(id, mDataServiceProvider));
	}

	public void getObservedByZipCode(String zipCode, IGuiRunnable<?> guiUpdateRunnable) {
		runnable = guiUpdateRunnable;
		task = new DataAsyncTask<ILocalRequestCallback<Observed>>();
		//TODO finish method
	}
	
	public void getReportingAreaByZipCode(String zipCode,IGuiRunnable<?> guiUpdateRunnable) {
		runnable = guiUpdateRunnable;
		task = new DataAsyncTask<ILocalRequestCallback<ReportingArea>>();
	}

	public void insertReportingArea(ReportingArea reportingArea) {
		task = new DataAsyncTask<ILocalRequestCallback<ReportingArea>>();
		task.execute(new CommandInsertReportingArea(mDataServiceProvider, reportingArea));
	}

	public void insertObserved(List<Observed> observedList) {
		// TODO Auto-generated method stub
		
	}

	public void insertForecast(List<Forecast> forecasts) {
		// TODO Auto-generated method stub
		
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
	
	class DataAsyncTask<T> extends AsyncTask<IDaoCommand<?>, Integer, ILocalRequestCallback<?>>{
		ILocalRequestCallback<?> callback;
		@Override
		protected ILocalRequestCallback<?> doInBackground(IDaoCommand<?>... arg0) {
			callback = (ILocalRequestCallback<?>) arg0[0].execute();
			
			if(runnable != null){
				runnable.setCallback(callback);
				mGuiHandler.post(runnable);
			}
			
			return callback;
		}
		
	}
}
