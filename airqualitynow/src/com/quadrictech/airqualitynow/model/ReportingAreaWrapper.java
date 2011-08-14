package com.quadrictech.airqualitynow.model;

import java.util.List;

public class ReportingAreaWrapper implements IReportingAreaWrapper{
	List<ReportingArea> mReportingAreas;
	
	public List<ReportingArea> getReportingArea() {
		return mReportingAreas;
	}

	public void setReportingArea(List<ReportingArea> reportingAreas) {
		mReportingAreas = reportingAreas;
	}
}
