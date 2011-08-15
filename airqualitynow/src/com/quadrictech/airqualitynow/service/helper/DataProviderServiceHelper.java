package com.quadrictech.airqualitynow.service.helper;

import roboguice.event.EventManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.widget.Toast;

import com.quadrictech.airqualitynow.base.callback.IDisposable;
import com.quadrictech.airqualitynow.db.callback.ForecastRequestCallback;
import com.quadrictech.airqualitynow.service.DataProviderService;

public class DataProviderServiceHelper implements IDataProviderServiceHelper, IDisposable{
	private Context mContext;
	private boolean mServiceBound;
	private EventManager mEventManager;
	
	public DataProviderServiceHelper(){
		
	}
	
	public DataProviderServiceHelper(Context context, EventManager eventManager){
		mContext = context;
		mEventManager = eventManager;
		doBindService();
	}
	
	public void getAllForecasts() {
		//IForecastRequestCallback callback= mDataServiceProvider.onGetAllReportingAreas();
		mEventManager.fire(mContext, new ForecastRequestCallback());
	}

	public void getForecastById(int id) {
	}

	public void getAllReportingAreas() {
		
	}
	
	private DataProviderService mDataServiceProvider;
	
	private ServiceConnection mConnection = new ServiceConnection(){

		public void onServiceConnected(ComponentName className, IBinder service) {
			 Toast.makeText(mContext, "connected", Toast.LENGTH_SHORT).show();
			mDataServiceProvider = ((DataProviderService.LocalBinder)service).getService();			
		}

		public void onServiceDisconnected(ComponentName className) {
			mDataServiceProvider = null;			
		}
		
	};

	public void doBindService() {
		mServiceBound = mContext.bindService(new Intent(mContext, DataProviderService.class), mConnection, Context.BIND_AUTO_CREATE);		
	}

	public void doUnBindService() {
		if(mServiceBound){
			mContext.unbindService(mConnection);
			mServiceBound = false;
		}
	}

	public void onDestroy() {
		doUnBindService();		
	}
	
}
