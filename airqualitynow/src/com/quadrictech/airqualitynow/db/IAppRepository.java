package com.quadrictech.airqualitynow.db;

public interface IAppRepository {
	public IStateRepository StateRepository();
	public IForecastRepository ForecastRepository();
	public IReportingAreaRepository ReportingAreaRepository();
	public IObservedRepository ObservedRepository();
}
