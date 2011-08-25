package com.quadrictech.airqualitynow.db;

import java.sql.SQLException;

public interface IAppRepository {
	public IForecastRepository ForecastRepository()throws SQLException;
	public IReportingAreaRepository ReportingAreaRepository()throws SQLException;
	public IObservedRepository ObservedRepository()throws SQLException;
	public IPollutantRepository PollutantRepository() throws SQLException;
}
