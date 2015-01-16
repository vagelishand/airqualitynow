package com.devterous.aqn.command;

import java.util.List;

import com.devterous.aqn.db.callback.IDataRequestCallback;
import com.devterous.aqn.model.Observation;
import com.devterous.aqn.model.ReportingArea;
import com.devterous.aqn.service.IDataProviderService;

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
