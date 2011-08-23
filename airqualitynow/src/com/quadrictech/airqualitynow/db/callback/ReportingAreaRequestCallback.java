package com.quadrictech.airqualitynow.db.callback;

import java.util.List;

import com.quadrictech.airqualitynow.model.ReportingArea;

public class ReportingAreaRequestCallback implements IDataRequestCallback<ReportingArea>{
	private List<ReportingArea> mReportingAreas;
	private Throwable       	  mException;

	public ReportingAreaRequestCallback(){
		
	}
	
	public void onError(Throwable exception) {
		mException = exception;
	}
	
	public boolean getErrorStatus() {
		return mException != null;
	}
	
	public String getErrorMessage() {
		return mException.getLocalizedMessage();
	}
	
	public void onResponseReceived(List<ReportingArea> response) {
		mReportingAreas = response;
	}
	
	public List<ReportingArea> getList() {
		return mReportingAreas;
	}
	
}
