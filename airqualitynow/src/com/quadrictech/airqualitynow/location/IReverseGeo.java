package com.quadrictech.airqualitynow.location;

import com.quadrictech.airqualitynow.db.callback.IDataRequestCallback;
import com.quadrictech.airqualitynow.inet.callback.RemoteCallbackData;

public interface IReverseGeo {
	IDataRequestCallback<RemoteCallbackData> getAddresses();
}
