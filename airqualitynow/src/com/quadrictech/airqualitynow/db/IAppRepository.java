package com.quadrictech.airqualitynow.db;

import java.sql.SQLException;

public interface IAppRepository {
	public IStateRepository StateRepository()throws SQLException;
	public IForecastRepository ForecastRepository()throws SQLException;
	public IReportingAreaRepository ReportingAreaRepository()throws SQLException;
	public IObservedRepository ObservedRepository()throws SQLException;
}
