package com.devterous.aqn.db;

import java.sql.SQLException;

public interface IAppRepository {
	public IForecastRepository ForecastRepository()throws SQLException;
	public IReportingAreaRepository ReportingAreaRepository()throws SQLException;
	public IObservationRepository ObservationRepository()throws SQLException;
	public IPollutantRepository PollutantRepository() throws SQLException;
	public void initialize(IReportingAreaRepository reportingAreaRepository);
	public void initialize(IForecastRepository forecastRepository);
	public void initialize(IObservationRepository ObservationRepository);
	public void initialize(IPollutantRepository pollutantRepository);
}
