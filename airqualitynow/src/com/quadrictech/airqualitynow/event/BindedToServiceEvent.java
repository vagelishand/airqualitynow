package com.quadrictech.airqualitynow.event;

import com.quadrictech.airqualitynow.service.helper.DataProviderServiceHelper;

public class BindedToServiceEvent implements IEvent<DataProviderServiceHelper> {
	private DataProviderServiceHelper mDataProviderServiceHelper;
	
	public BindedToServiceEvent(DataProviderServiceHelper helper){
		mDataProviderServiceHelper = helper;
	}
	
	public DataProviderServiceHelper getSender() {
		return mDataProviderServiceHelper;
	}

}
