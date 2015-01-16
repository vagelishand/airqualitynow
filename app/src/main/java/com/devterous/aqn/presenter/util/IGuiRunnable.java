package com.devterous.aqn.presenter.util;

import com.devterous.aqn.db.callback.IDataRequestCallback;

public interface IGuiRunnable<T> extends Runnable{
	public void setCallback(IDataRequestCallback<?> callback);
}