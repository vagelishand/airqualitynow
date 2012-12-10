package com.quadrictech.airqualitynow.command;

import com.quadrictech.airqualitynow.db.callback.IDataRequestCallback;
import com.quadrictech.airqualitynow.inet.callback.RemoteCallbackData;
import com.quadrictech.airqualitynow.location.IReverseGeo;

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
