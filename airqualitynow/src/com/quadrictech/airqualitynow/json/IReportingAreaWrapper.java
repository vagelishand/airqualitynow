package com.quadrictech.airqualitynow.json;

import java.util.List;

import com.quadrictech.airqualitynow.model.ReportingArea;

public interface IReportingAreaWrapper {
	public List<ReportingArea> getReportingArea();
	public void setReportingArea(List<ReportingArea> reportingAreas);
}
