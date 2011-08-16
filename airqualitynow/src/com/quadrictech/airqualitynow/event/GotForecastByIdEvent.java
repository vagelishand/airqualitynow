package com.quadrictech.airqualitynow.event;

import com.quadrictech.airqualitynow.db.callback.ILocalRequestCallback;
import com.quadrictech.airqualitynow.model.Forecast;
import com.quadrictech.airqualitynow.service.helper.DataProviderServiceHelper;

public class GotForecastByIdEvent implements IEvent<DataProviderServiceHelper> {
	DataProviderServiceHelper mDataProviderServiceHelper;	
	public ILocalRequestCallback<Forecast> mRequestCallback;
	
	public GotForecastByIdEvent(DataProviderServiceHelper dataProviderServiceHelper){
		mDataProviderServiceHelper = dataProviderServiceHelper;
	}
	
	public DataProviderServiceHelper getSender() {
		return mDataProviderServiceHelper;
	}

}
