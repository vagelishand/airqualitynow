package com.quadrictech.airqualitynow.command;

import java.util.List;

import com.quadrictech.airqualitynow.db.callback.IDataRequestCallback;
import com.quadrictech.airqualitynow.model.Observed;
import com.quadrictech.airqualitynow.service.IDataProviderService;

public class CommandInsertObserved extends DaoCommand<IDataRequestCallback<Observed>> {
	private List<Observed> mObservedList;
	
	public CommandInsertObserved(List<Observed> observedList, IDataProviderService dataProviderService){
		mObservedList = observedList;
		mDataProviderService = dataProviderService;
	}
	
	public IDataRequestCallback<Observed> execute() {
		return mDataProviderService.insertObserved(mObservedList);
	}

}
