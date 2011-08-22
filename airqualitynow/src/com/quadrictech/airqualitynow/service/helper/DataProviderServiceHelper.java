package com.quadrictech.airqualitynow.service.helper;

import java.util.List;

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
import com.quadrictech.airqualitynow.command.CommandGetReportingAreaByZipCode;
import com.quadrictech.airqualitynow.command.CommandInsertReportingArea;
import com.quadrictech.airqualitynow.command.IDaoCommand;
import com.quadrictech.airqualitynow.db.callback.IDataRequestCallback;
import com.quadrictech.airqualitynow.model.Forecast;
import com.quadrictech.airqualitynow.model.Observed;
import com.quadrictech.airqualitynow.model.ReportingArea;
import com.quadrictech.airqualitynow.presenter.util.IGuiRunnable;
import com.quadrictech.airqualitynow.service.DataProviderService;
import com.quadrictech.airqualitynow.service.IDataProviderService;

public class DataProviderServiceHelper implements IDataProviderServiceHelper, ServiceConnection, IDisposable{
	private Context mContext;
	private boolean mServiceBound;
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
	
	public void getAllReportingAreas(IGuiRunnable<?> guiUpdateRunnable) {
		runnable = guiUpdateRunnable;
		task = new DataAsyncTask<IDataRequestCallback<ReportingArea>>();
		task.execute(new CommandGetAllReportingAreas(mDataServiceProvider));		
	}
	
	public void getForecastById(int id, IGuiRunnable<?> guiUpdateRunnable) {
		runnable = guiUpdateRunnable;
		task = new DataAsyncTask<IDataRequestCallback<Forecast>>();
		task.execute(new CommandGetForecastById(id, mDataServiceProvider));
	}

	public void getObservedByZipCode(String zipCode, IGuiRunnable<?> guiUpdateRunnable) {
		runnable = guiUpdateRunnable;
		task = new DataAsyncTask<IDataRequestCallback<Observed>>();
		//TODO finish method
	}
	
	public void getReportingAreaByZipCode(String zipCode,IGuiRunnable<?> guiUpdateRunnable) {
		runnable = guiUpdateRunnable;
		task = new DataAsyncTask<IDataRequestCallback<ReportingArea>>();
		task.execute(new CommandGetReportingAreaByZipCode(zipCode, mDataServiceProvider));
	}

	public void insertReportingArea(ReportingArea reportingArea, IGuiRunnable<?> guiUpdateRunnable) {
		runnable = guiUpdateRunnable;
		task = new DataAsyncTask<IDataRequestCallback<ReportingArea>>();
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
	}

	public void onServiceDisconnected(ComponentName className) {
		mDataServiceProvider = null;			
	}

	public void doBindService(Context context) {
		mContext = context;
		
		if(!mServiceBound){
			mServiceBound = mContext.bindService(new Intent(mContext, DataProviderService.class), this, Context.BIND_AUTO_CREATE);
		}
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
	
	class DataAsyncTask<T> extends AsyncTask<IDaoCommand<?>, Integer, IDataRequestCallback<?>>{
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
