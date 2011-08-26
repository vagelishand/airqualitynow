package com.quadrictech.airqualitynow.service.helper;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import android.app.ProgressDialog;
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
import com.quadrictech.airqualitynow.command.CommandGetObservationAndForecastByReportingArea;
import com.quadrictech.airqualitynow.command.CommandGetObservationById;
import com.quadrictech.airqualitynow.command.CommandGetReportingAreaByZipCode;
import com.quadrictech.airqualitynow.command.CommandInsertForecast;
import com.quadrictech.airqualitynow.command.CommandInsertObservation;
import com.quadrictech.airqualitynow.command.CommandInsertReportingArea;
import com.quadrictech.airqualitynow.command.IDaoCommand;
import com.quadrictech.airqualitynow.db.callback.IDataRequestCallback;
import com.quadrictech.airqualitynow.model.Forecast;
import com.quadrictech.airqualitynow.model.Observation;
import com.quadrictech.airqualitynow.model.ReportingArea;
import com.quadrictech.airqualitynow.model.viewmodel.ObservationAndForecast;
import com.quadrictech.airqualitynow.presenter.util.IGuiRunnable;
import com.quadrictech.airqualitynow.service.DataProviderService;
import com.quadrictech.airqualitynow.service.IDataProviderService;

public class DataProviderServiceHelper implements IDataProviderServiceHelper, ServiceConnection, IDisposable{
	private Context mServiceContext;
	private Context mContext;
	private boolean mServiceBound;
	private IDataProviderService mDataServiceProvider;
	DataAsyncTask<?> task;
	IGuiRunnable<?> runnable;
	public final Handler mGuiHandler = new Handler();
	public final Handler mDmoHandler = new Handler();
	
	private static class SingletonHolder
    {
		private final static DataProviderServiceHelper INSTANCE = new DataProviderServiceHelper();
    }

	public static synchronized DataProviderServiceHelper getInstance(){
		return SingletonHolder.INSTANCE;
	}
	
	private DataProviderServiceHelper(){
		
	}
	
	public void setWindowContext(Context context){
		mContext = context;
	}
	
	public DataProviderServiceHelper(IDataProviderService dataProviderService){
		mDataServiceProvider = dataProviderService;
	}
	
	public void getAllReportingAreas(IGuiRunnable<?> guiUpdateRunnable) {
		runnable = guiUpdateRunnable;
		task = new DataAsyncTask<IDataRequestCallback<ReportingArea>>();
		task.execute(new CommandGetAllReportingAreas(mDataServiceProvider));		
	}	
	
	public void getForecastsByReportingAreaId(int id, Date issueDate, IGuiRunnable<?> guiUpdateRunnable) {
		runnable = guiUpdateRunnable;
		task = new DataAsyncTask<IDataRequestCallback<Forecast>>();
		task.execute(new CommandGetForecastById(id, issueDate, mDataServiceProvider));
	}
	
	public void getObservationsByReportingAreaId(int id, Date issueDate, IGuiRunnable<?> guiUpdateRunnable) {
		runnable = guiUpdateRunnable;
		task = new DataAsyncTask<IDataRequestCallback<Observation>>();
		task.execute(new CommandGetObservationById(id, issueDate, mDataServiceProvider));
	}
	
	public void getObservationAndForecastByReportingAreaId(int id, Date issueDate, IGuiRunnable<?> guiUpdateRunnable) {
		runnable = guiUpdateRunnable;
		task = new DataAsyncTask<IDataRequestCallback<ObservationAndForecast>>();
		task.execute(new CommandGetObservationAndForecastByReportingArea(id, issueDate, mDataServiceProvider));
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

	public void insertObservations(ReportingArea reportingArea, List<Observation> observations, IGuiRunnable<?> guiUpdateRunnable) {
		runnable = guiUpdateRunnable;
		
		for(Observation o: observations){
			o.ReportingAreaObject = reportingArea;
		}
		
		task = new DataAsyncTask<IDataRequestCallback<Observation>>();
		task.execute(new CommandInsertObservation(observations, mDataServiceProvider));
	}

	public void insertForecasts(ReportingArea reportingArea, List<Forecast> forecasts, IGuiRunnable<?> guiUpdateRunnable) {
		runnable = guiUpdateRunnable;
		
		for(Forecast f: forecasts){
			f.ReportingAreaObject = reportingArea;
		}
		
		task = new DataAsyncTask<IDataRequestCallback<Forecast>>();
		task.execute(new CommandInsertForecast(forecasts, mDataServiceProvider));
	}
	
	public void onServiceConnected(ComponentName className, IBinder service) {
		mDataServiceProvider = ((DataProviderService.LocalBinder)service).getService();
	}

	public void onServiceDisconnected(ComponentName className) {
		mDataServiceProvider = null;			
	}

	public void doBindService(Context context) {
		if(!mServiceBound){
			mServiceContext = context;
			mServiceBound = mServiceContext.bindService(new Intent(mServiceContext, DataProviderService.class), this, Context.BIND_AUTO_CREATE);
		}
	}

	public void doUnBindService() {
		if(mServiceBound){
			mServiceContext.unbindService(this);
			mServiceBound = false;
		}
	}

	public void onDestroy() {
		doUnBindService();		
	}
	
	class DataAsyncTask<T> extends AsyncTask<IDaoCommand<?>, Integer, IDataRequestCallback<?>>{
		IDataRequestCallback<?> callback;
		ProgressDialog dialog;
		
		protected void onPreExecute(){
			dialog = new ProgressDialog(mContext);
			dialog.setTitle("Processing...");
			dialog.setMessage("Please be patient...");
			dialog.show();
		}
		
		@Override
		protected IDataRequestCallback<?> doInBackground(IDaoCommand<?>... arg0) {
			
			callback = (IDataRequestCallback<?>) arg0[0].execute();
		
			if(runnable != null){
				runnable.setCallback(callback);
				mGuiHandler.post(runnable);
			}
			
			return callback;
		}
		
		protected void onPostExecute(IDataRequestCallback<?> callback){
			dialog.dismiss();
		}
	}

	public ReportingArea insertReportingArea(ReportingArea reportingArea) throws SQLException {
		return mDataServiceProvider.insertReportArea(reportingArea);
	}
}
