package com.quadrictech.airqualitynow.command;

import java.util.List;

import com.quadrictech.airqualitynow.db.callback.IDataRequestCallback;
import com.quadrictech.airqualitynow.model.Observation;
import com.quadrictech.airqualitynow.model.ReportingArea;
import com.quadrictech.airqualitynow.service.IDataProviderService;

public class CommandInsertObservation extends DaoCommand<IDataRequestCallback<Observation>> {
	private List<Observation> mObservations;
	private ReportingArea mReportingArea;
	
	public CommandInsertObservation(ReportingArea reportingArea, List<Observation> observations, IDataProviderService dataProviderService){
		mReportingArea = reportingArea;
		mObservations = observations;
		mDataProviderService = dataProviderService;
	}
	
	public IDataRequestCallback<Observation> execute() {
		return mDataProviderService.insertObservations(mReportingArea, mObservations);
	}

}
