package com.quadrictech.airqualitynow.location;

import java.util.List;

import android.content.Context;
import android.widget.Toast;

import com.quadrictech.airqualitynow.db.callback.IDataRequestCallback;
import com.quadrictech.airqualitynow.inet.callback.RemoteCallbackData;
import com.quadrictech.airqualitynow.settings.AppPreferences;
import com.quadrictech.airqualitynow.settings.IPreferences;
import android.location.Address;

public class ReverseGeoRunnable implements Runnable {
	private IDataRequestCallback<?> mCallback;
	private Context mContext;
	
	public ReverseGeoRunnable(Context context){
		mContext = context;
	}
	
	public void run() {
		
		if(mCallback.getErrorStatus()){
			Toast.makeText(mContext, mCallback.getErrorMessage(), Toast.LENGTH_SHORT).show();
		}
		else{
			IPreferences pref = new AppPreferences(mContext);
			List<RemoteCallbackData> callback  = (List<RemoteCallbackData>) mCallback.getList();
			RemoteCallbackData data = callback.get(0);

			pref.setDefaultReportingAreaZipCode(data.addresses.get(0).getPostalCode());
		}
	}

	public void setCallback(IDataRequestCallback<?> callback){
		mCallback = callback;
	}
}
