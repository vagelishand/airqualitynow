package com.quadrictech.airqualitynow.json;

import java.util.List;
import com.quadrictech.airqualitynow.model.ReportingArea;

public class ReportingAreaWrapper implements IReportingAreaWrapper{
	List<ReportingArea> mReportingAreas;
	
	public List<ReportingArea> getReportingArea() {
		return mReportingAreas;
	}

	public void setReportingArea(List<ReportingArea> reportingAreas) {
		mReportingAreas = reportingAreas;
	}
}
