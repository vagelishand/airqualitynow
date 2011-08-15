package com.quadrictech.airqualitynow.db.callback;

import java.util.List;

import com.quadrictech.airqualitynow.base.callback.IRequestCallback;
import com.quadrictech.airqualitynow.model.IReportingAreaWrapper;
import com.quadrictech.airqualitynow.model.ReportingArea;

public class ReportingAreaRequestCallback implements IRequestCallback<ReportingArea>{
	private IReportingAreaWrapper mReportingAreaWrapper;
	private ReportingArea         mReportingArea;
	private Throwable       	  mException;
	
	public ReportingAreaRequestCallback(ReportingArea reportingArea){
		mReportingArea = reportingArea;
	}
	
	public ReportingAreaRequestCallback(IReportingAreaWrapper reportingAreaWrapper){
		mReportingAreaWrapper = reportingAreaWrapper;
	}
	
	public void onError(Throwable exception) {
		// TODO Auto-generated method stub
		
	}

	public void onResponseReceived(ReportingArea response) {
		// TODO Auto-generated method stub
		
	}

	public List<ReportingArea> getList() {
		return mReportingAreaWrapper.getReportingArea();
	}

	public ReportingArea getReportingArea() {
		return mReportingArea;
	}

	public boolean getErrorStatus() {
		return !(mException == null);
	}

	public String getErrorMessage() {
		return mException.getLocalizedMessage();
	}

}
