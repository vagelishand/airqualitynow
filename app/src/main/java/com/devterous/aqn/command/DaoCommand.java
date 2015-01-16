package com.devterous.aqn.command;

import com.devterous.aqn.service.IDataProviderService;

public abstract class DaoCommand<T> implements IDaoCommand<T> {
	protected IDataProviderService mDataProviderService;
}
