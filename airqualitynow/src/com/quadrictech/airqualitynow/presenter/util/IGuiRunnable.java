package com.quadrictech.airqualitynow.presenter.util;

import com.quadrictech.airqualitynow.db.callback.IDataRequestCallback;

public interface IGuiRunnable<T> extends Runnable{
	public void setCallback(IDataRequestCallback<?> callback);
}