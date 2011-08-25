package com.quadrictech.airqualitynow.db;

import java.sql.SQLException;

import com.j256.ormlite.support.ConnectionSource;

public class AppRepository implements IAppRepository {
	ConnectionSource mConnectionSource;
	IForecastRepository mForecastRepository;
	IReportingAreaRepository mReportingAreaRepository;
	IObservedRepository mObservedRepository;
	IPollutantRepository mPollutantRepository;
		
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
	
	public IObservedRepository ObservedRepository() throws SQLException{
		if(mObservedRepository == null){
			mObservedRepository = new ObservedRepository(mConnectionSource);
		}
		
		return mObservedRepository;
	}

	public IPollutantRepository PollutantRepository() throws SQLException {
		if(mPollutantRepository == null){
			mPollutantRepository = new PollutantRepository(mConnectionSource);
		}
		
		return mPollutantRepository;
	}
}
