package com.devterous.aqn.location;

import com.devterous.aqn.db.callback.IDataRequestCallback;
import com.devterous.aqn.inet.callback.RemoteCallbackData;

public interface IReverseGeo {
	IDataRequestCallback<RemoteCallbackData> getAddresses();
}
