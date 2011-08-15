package com.quadrictech.airqualitynow.event;

import com.quadrictech.airqualitynow.base.callback.IRequestCallback;
import com.quadrictech.airqualitynow.model.ReportingArea;
import com.quadrictech.airqualitynow.service.helper.DataProviderServiceHelper;

public class GotAllReportingAreasEvent implements IEvent<DataProviderServiceHelper> {
	DataProviderServiceHelper mDataProviderServiceHelper;
	public IRequestCallback<ReportingArea> mRequestCallback;
	
	public GotAllReportingAreasEvent(DataProviderServiceHelper dataProviderServiceHelper){
		mDataProviderServiceHelper = dataProviderServiceHelper;
	}
	
	public DataProviderServiceHelper getSender() {
		return mDataProviderServiceHelper;		
	}

}
