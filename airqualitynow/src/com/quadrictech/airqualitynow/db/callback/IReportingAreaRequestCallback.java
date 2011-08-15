package com.quadrictech.airqualitynow.db.callback;

import java.util.List;

import com.quadrictech.airqualitynow.base.callback.IRequestCallback;
import com.quadrictech.airqualitynow.model.ReportingArea;

public interface IReportingAreaRequestCallback extends IRequestCallback<ReportingArea> {
	public ReportingArea getReportingArea();
	public List<ReportingArea> getReportingAreas();
}
