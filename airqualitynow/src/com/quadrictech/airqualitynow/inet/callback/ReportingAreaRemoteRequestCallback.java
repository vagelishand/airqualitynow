package com.quadrictech.airqualitynow.inet.callback;

import java.util.List;

import com.quadrictech.airqualitynow.model.IReportingAreaWrapper;
import com.quadrictech.airqualitynow.model.ReportingArea;
import com.quadrictech.airqualitynow.model.ReportingAreaWrapper;
import com.quadrictech.airqualitynow.db.callback.IDataRequestCallback;

public class ReportingAreaRemoteRequestCallback implements IDataRequestCallback<ReportingArea> {
	IReportingAreaWrapper mReportingAreaWrapper;
	ReportingArea mReportingArea;
	Throwable mException;
	
	public ReportingAreaRemoteRequestCallback(){
		mReportingAreaWrapper = new ReportingAreaWrapper();
	}
	
	public void onError(Throwable exception) {
		mException = exception;		
	}

	public List<ReportingArea> getList() {
		return mReportingAreaWrapper.getReportingArea();
	}

	public boolean getErrorStatus() {
		return !(mException == null);
	}

	public String getErrorMessage() {
		return mException.getLocalizedMessage();
	}

	public void onResponseReceived(List<ReportingArea> response) {
		mReportingAreaWrapper.setReportingArea(response);
	}

}
