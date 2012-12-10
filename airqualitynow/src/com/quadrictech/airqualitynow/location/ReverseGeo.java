package com.quadrictech.airqualitynow.location;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import com.quadrictech.airqualitynow.db.callback.IDataRequestCallback;
import com.quadrictech.airqualitynow.inet.callback.GeoRequestCallback;
import com.quadrictech.airqualitynow.inet.callback.RemoteCallbackData;

import android.content.Context;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Message;

public class ReverseGeo implements IReverseGeo {
	Context mContext;
	Location mLocation;
	
	public ReverseGeo(Context context, Location location) {
		mContext = context;
		mLocation = location;
	}

	public IDataRequestCallback<RemoteCallbackData> getAddresses() {
		
		IDataRequestCallback<RemoteCallbackData> callback = new GeoRequestCallback();
		List<RemoteCallbackData> response;

		Geocoder geocoder = new Geocoder(mContext, Locale.getDefault());

        List<Address> addresses = null;
        try {
            // Call the synchronous getFromLocation() method by passing in the lat/long values.
            addresses = geocoder.getFromLocation(mLocation.getLatitude(), mLocation.getLongitude(), 1);
        } catch (IOException e) {
            e.printStackTrace();
            // Update UI field with the exception.
            //Message.obtain(mHandler, UPDATE_ADDRESS, e.toString()).sendToTarget();
        }
		
        RemoteCallbackData callbackData = new RemoteCallbackData();
        response = new ArrayList<RemoteCallbackData>();
        callbackData.addresses = addresses;
        
        response.add(callbackData);
		callback.onResponseReceived(response);
		
		return callback;
	}


}
