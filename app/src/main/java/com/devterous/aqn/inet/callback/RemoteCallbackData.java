package com.devterous.aqn.inet.callback;

import java.util.List;

import android.location.Address;

import com.devterous.aqn.model.Forecast;
import com.devterous.aqn.model.Observation;
import com.devterous.aqn.model.ReportingArea;

public class RemoteCallbackData {
	public ReportingArea reportingArea;
	public List<Forecast> forecasts;
	public List<Observation> observations;
	public List<Address> addresses;
}
