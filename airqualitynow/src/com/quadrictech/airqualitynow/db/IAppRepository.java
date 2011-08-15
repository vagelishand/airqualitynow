package com.quadrictech.airqualitynow.db;

import com.quadrictech.airqualitynow.model.Forecast;
import com.quadrictech.airqualitynow.model.ReportingArea;
import com.quadrictech.airqualitynow.model.State;

public interface IAppRepository {
	public Repository<State> StateRepository();
	public Repository<Forecast> ForecastRepository();
	public Repository<ReportingArea> ReportingAreaRepository();
}
