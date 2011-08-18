package com.quadrictech.airqualitynow.db;

import java.sql.SQLException;

import com.j256.ormlite.support.ConnectionSource;

public class AppRepository implements IAppRepository {
	ConnectionSource mConnectionSource;
	IForecastRepository mForecastRepository;
	IReportingAreaRepository mReportingAreaRepository;
	IStateRepository mStateRepository;
	IObservedRepository mObservedRepository;
		
	public AppRepository(ConnectionSource connectionSource){
		mConnectionSource = connectionSource;
	}
	
	public IStateRepository StateRepository() throws SQLException{
		if(mStateRepository == null){
			mStateRepository = new StateRepository(mConnectionSource);
		}
		
		return mStateRepository;
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
}
