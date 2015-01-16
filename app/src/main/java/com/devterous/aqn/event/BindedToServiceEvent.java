package com.devterous.aqn.event;

import com.devterous.aqn.service.helper.DataProviderServiceHelper;

public class BindedToServiceEvent implements IEvent<DataProviderServiceHelper> {
	private DataProviderServiceHelper mDataProviderServiceHelper;
	
	public BindedToServiceEvent(DataProviderServiceHelper helper){
		mDataProviderServiceHelper = helper;
	}
	
	public DataProviderServiceHelper getSender() {
		return mDataProviderServiceHelper;
	}

}
