package com.quadrictech.airqualitynow.location;

import com.quadrictech.airqualitynow.command.CommandGetReverseGeoAddresses;
import com.quadrictech.airqualitynow.command.IDaoCommand;
import com.quadrictech.airqualitynow.db.callback.IDataRequestCallback;

import android.content.Context;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.location.LocationProvider;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Toast;

public class ReverseGeoHelper implements IReverseGeoHelper {
	private static ReverseGeoHelper mReverseGeoHelper;
	private IReverseGeo mReverseGeo;
	private final Handler mHandler = new Handler();
	
	public static ReverseGeoHelper getInstance(){
		if(mReverseGeoHelper == null){
			mReverseGeoHelper = new ReverseGeoHelper(); 
		}
		return mReverseGeoHelper;
	}
	
	public void getAddresses(final Context context) {
		final ReverseGeoRunnable runnable = new ReverseGeoRunnable(context);
		LocationManager locationManager =
		        (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
		
		// Or use LocationManager.GPS_PROVIDER

		Location location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
		
		if(location == null){
			return;
		}
		
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.GINGERBREAD && Geocoder.isPresent()) {
            // Since the geocoding API is synchronous and may take a while.  You don't want to lock
            // up the UI thread.  Invoking reverse geocoding in an AsyncTask.
            //(new ReverseGeocodingTask(this)).execute(new Location[] {location});
        	
        	mReverseGeo = new ReverseGeo(context, location);
    		RemoteAsyncTask task = new RemoteAsyncTask(runnable);
    		task.execute(new CommandGetReverseGeoAddresses(mReverseGeo));
        }
	}
	
	class RemoteAsyncTask extends AsyncTask<IDaoCommand<?>, Integer, IDataRequestCallback<?>>{
		IDataRequestCallback<?> callback;
		ReverseGeoRunnable mRunnable;		
		
		public RemoteAsyncTask(ReverseGeoRunnable runnable){
			mRunnable = runnable;
		}
		
		@Override
		protected IDataRequestCallback<?> doInBackground(
				IDaoCommand<?>... arg0) {
			callback = (IDataRequestCallback<?>) arg0[0].execute();
			mRunnable.setCallback(callback);
			mHandler.post(mRunnable);
			
			return callback;
		}
		
	}	

}
