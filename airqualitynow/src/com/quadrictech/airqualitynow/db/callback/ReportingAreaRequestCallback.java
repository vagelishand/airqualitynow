package com.quadrictech.airqualitynow.db.callback;

import java.util.List;

import com.quadrictech.airqualitynow.model.IReportingAreaWrapper;
import com.quadrictech.airqualitynow.model.ReportingArea;
import com.quadrictech.airqualitynow.model.ReportingAreaWrapper;

public class ReportingAreaRequestCallback implements IDataRequestCallback<ReportingArea>{
	private IReportingAreaWrapper mReportingAreaWrapper;
	private Throwable       	  mException;

	public ReportingAreaRequestCallback(){
		mReportingAreaWrapper = new ReportingAreaWrapper();
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
		mReportingAreaWrapper.setReportingArea(response);
	}
	
	public List<ReportingArea> getList() {
		return mReportingAreaWrapper.getReportingArea();
	}
	
}
