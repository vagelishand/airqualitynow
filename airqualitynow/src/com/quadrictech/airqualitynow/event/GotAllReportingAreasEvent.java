package com.quadrictech.airqualitynow.event;

import com.quadrictech.airqualitynow.db.callback.ILocalRequestCallback;
import com.quadrictech.airqualitynow.model.ReportingArea;
import com.quadrictech.airqualitynow.service.helper.DataProviderServiceHelper;

public class GotAllReportingAreasEvent implements IEvent<DataProviderServiceHelper> {
	DataProviderServiceHelper mDataProviderServiceHelper;
	public ILocalRequestCallback<ReportingArea> mRequestCallback;
	
	public GotAllReportingAreasEvent(DataProviderServiceHelper dataProviderServiceHelper){
		mDataProviderServiceHelper = dataProviderServiceHelper;
	}
	
	public DataProviderServiceHelper getSender() {
		return mDataProviderServiceHelper;		
	}

}
