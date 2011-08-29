package com.quadrictech.airqualitynow.command;

import java.util.List;

import com.quadrictech.airqualitynow.db.callback.IDataRequestCallback;
import com.quadrictech.airqualitynow.model.Observation;
import com.quadrictech.airqualitynow.service.IDataProviderService;

public class CommandInsertObservation extends DaoCommand<IDataRequestCallback<Observation>> {
	private List<Observation> mObservations;
	
	public CommandInsertObservation(List<Observation> observations, IDataProviderService dataProviderService){
		mObservations = observations;
		mDataProviderService = dataProviderService;
	}
	
	public IDataRequestCallback<Observation> execute() {
		return mDataProviderService.insertObservations(null, mObservations);
	}

}
