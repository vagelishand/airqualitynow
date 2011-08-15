package com.quadrictech.airqualitynow.event;

import com.quadrictech.airqualitynow.base.callback.IRequestCallback;
import com.quadrictech.airqualitynow.model.Forecast;
import com.quadrictech.airqualitynow.service.helper.DataProviderServiceHelper;

public class GotAllForecastsEvent implements IEvent<DataProviderServiceHelper> {
	DataProviderServiceHelper mDataProviderServiceHelper;	
	public IRequestCallback<Forecast> mRequestCallback;
	
	public GotAllForecastsEvent(DataProviderServiceHelper dataProviderServiceHelper){
		mDataProviderServiceHelper = dataProviderServiceHelper;
	}
	
	public DataProviderServiceHelper getSender() {
		return mDataProviderServiceHelper;
	}

}
