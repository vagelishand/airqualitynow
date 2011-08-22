package com.quadrictech.airqualitynow.db.callback;

import com.quadrictech.airqualitynow.base.callback.IRequestCallback;
import com.quadrictech.airqualitynow.base.callback.IRequestCallbackListable;

public interface IDataRequestCallback<T> extends IRequestCallback<T>, IRequestCallbackListable<T>{
	
}