package com.quadrictech.airqualitynow.command;

import com.quadrictech.airqualitynow.service.IRemoteDataProviderService;

public abstract class RemoteDaoCommand<T> implements IDaoCommand<T> {
	protected IRemoteDataProviderService mRemoteDataProviderService;
}
