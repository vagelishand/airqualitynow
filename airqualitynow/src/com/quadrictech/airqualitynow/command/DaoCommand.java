package com.quadrictech.airqualitynow.command;

import android.content.Context;
import android.os.Handler;

import com.quadrictech.airqualitynow.service.IDataProviderService;

public abstract class DaoCommand<T> implements IDaoCommand<T> {
	protected IDataProviderService mDataProviderService;
	protected Context mContext;
	protected Handler mHandler;
}
