package com.devterous.aqn.command;

import com.devterous.aqn.db.callback.IDataRequestCallback;
import com.devterous.aqn.inet.callback.RemoteCallbackData;
import com.devterous.aqn.location.IReverseGeo;

public class CommandGetReverseGeoAddresses extends 
RemoteDaoCommand<IDataRequestCallback<RemoteCallbackData>> {
	IReverseGeo mReverseGeo;
	
	public CommandGetReverseGeoAddresses(IReverseGeo reverseGeo){
		mReverseGeo = reverseGeo;
	}	
	
	public IDataRequestCallback<RemoteCallbackData> execute() {
		return mReverseGeo.getAddresses();
	}

}
