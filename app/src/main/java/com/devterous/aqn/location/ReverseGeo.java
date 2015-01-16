package com.devterous.aqn.location;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import com.devterous.aqn.db.callback.IDataRequestCallback;
import com.devterous.aqn.inet.callback.GeoRequestCallback;
import com.devterous.aqn.inet.callback.RemoteCallbackData;

import android.content.Context;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;

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
		RemoteCallbackData callbackData = new RemoteCallbackData();
		Geocoder geocoder = new Geocoder(mContext, Locale.getDefault());

        List<Address> addresses = null;
        try {
            addresses = geocoder.getFromLocation(mLocation.getLatitude(), mLocation.getLongitude(), 1);
        } catch (IOException e) {
        	callback.onError(e);
        	return callback;
        }
        
        response = new ArrayList<RemoteCallbackData>();
        callbackData.addresses = addresses;
        
        response.add(callbackData);
		callback.onResponseReceived(response);
		
		return callback;
	}


}
