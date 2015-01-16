package com.devterous.aqn.db;

import java.sql.SQLException;
import com.j256.ormlite.support.ConnectionSource;

public class AppRepository implements IAppRepository {
	ConnectionSource mConnectionSource;
	IForecastRepository mForecastRepository;
	IReportingAreaRepository mReportingAreaRepository;
	IObservationRepository mObservationRepository;
	IPollutantRepository mPollutantRepository;
	
	public AppRepository()
	{
		
	}
	
	public AppRepository(ConnectionSource connectionSource){
		mConnectionSource = connectionSource;
	}
	
	public IForecastRepository ForecastRepository() throws SQLException {
		if(mForecastRepository == null){
			mForecastRepository = new ForecastRepository(mConnectionSource);
		}		
		
		return mForecastRepository;
	}

	public IReportingAreaRepository ReportingAreaRepository() throws SQLException {
		if(mReportingAreaRepository == null){
			mReportingAreaRepository = new ReportingAreaRepository(mConnectionSource);
		}
		return mReportingAreaRepository;
	}
	
	public IObservationRepository ObservationRepository() throws SQLException{
		if(mObservationRepository == null){
			mObservationRepository = new ObservationRepository(mConnectionSource);
		}
		
		return mObservationRepository;
	}

	public IPollutantRepository PollutantRepository() throws SQLException {
		if(mPollutantRepository == null){
			mPollutantRepository = new PollutantRepository(mConnectionSource);
		}
		
		return mPollutantRepository;
	}

	public void initialize(IReportingAreaRepository reportingAreaRepository) {
		mReportingAreaRepository = reportingAreaRepository;
	}

	public void initialize(IForecastRepository forecastRepository) {
		mForecastRepository = forecastRepository;
	}

	public void initialize(IObservationRepository observationRepository) {
		mObservationRepository = observationRepository;
	}

	public void initialize(IPollutantRepository pollutantRepository) {
		this.mPollutantRepository = pollutantRepository;
	}
}
