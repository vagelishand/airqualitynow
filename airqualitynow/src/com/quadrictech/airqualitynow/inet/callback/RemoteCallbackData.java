package com.quadrictech.airqualitynow.inet.callback;

import java.util.List;

import com.quadrictech.airqualitynow.model.Forecast;
import com.quadrictech.airqualitynow.model.Observation;
import com.quadrictech.airqualitynow.model.ReportingArea;

public class RemoteCallbackData {
	public ReportingArea reportingArea;
	public List<Forecast> forecasts;
	public List<Observation> observations;
}
