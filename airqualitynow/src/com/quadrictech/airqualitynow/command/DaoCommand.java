package com.quadrictech.airqualitynow.command;

import com.quadrictech.airqualitynow.service.IDataProviderService;

public abstract class DaoCommand<T> implements IDaoCommand<T> {
	protected IDataProviderService mDataProviderService;
}
