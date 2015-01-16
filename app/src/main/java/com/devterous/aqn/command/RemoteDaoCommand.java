package com.devterous.aqn.command;

import com.devterous.aqn.service.IRemoteDataProviderService;

public abstract class RemoteDaoCommand<T> implements IDaoCommand<T> {
	protected IRemoteDataProviderService mRemoteDataProviderService;
}
