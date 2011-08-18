package com.quadrictech.airqualitynow.presenter.util;

import com.quadrictech.airqualitynow.db.callback.ILocalRequestCallback;

public interface IGuiRunnable<T> extends Runnable{
	public void setCallback(ILocalRequestCallback<?> callback);
}