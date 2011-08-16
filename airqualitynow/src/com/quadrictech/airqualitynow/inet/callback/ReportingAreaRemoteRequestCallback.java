package com.quadrictech.airqualitynow.inet.callback;

import java.util.List;

import com.quadrictech.airqualitynow.model.ReportingArea;
import com.quadrictech.airqualitynow.model.ReportingAreaWrapper;

public class ReportingAreaRemoteRequestCallback implements IRemoteRequestCallback<ReportingArea> {
	ReportingAreaWrapper mReportingAreaWrapper;
	ReportingArea mReportingArea;
	Throwable mException;
	
	public void onError(Throwable exception) {
		mException = exception;		
	}

	public void onResponseReceived(ReportingArea response) {
		mReportingArea = response;
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

}
