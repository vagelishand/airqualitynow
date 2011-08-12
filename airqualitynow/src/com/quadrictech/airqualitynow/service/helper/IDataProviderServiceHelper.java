package com.quadrictech.airqualitynow.service.helper;

import java.util.List;

import com.quadrictech.airqualitynow.model.Forecast;
import com.quadrictech.airqualitynow.model.ReportingArea;

public interface IDataProviderServiceHelper extends IServiceHelper {
	public List<Forecast> getAllForecasts();
	public Forecast getForecastById(int id);
	//public List<ReportingArea> getAllReportingAreas();
}
