package com.quadrictech.airqualitynow.db;

import java.sql.SQLException;

import com.j256.ormlite.support.ConnectionSource;

public class AppRepository implements IAppRepository {
	ConnectionSource mConnectionSource;
	IForecastRepository mForecastRepository;
	IReportingAreaRepository mReportingAreaRepository;
	IStateRepository mStateRepository;
		
	public AppRepository(ConnectionSource connectionSource){
		mConnectionSource = connectionSource;
	}
	
	public IStateRepository StateRepository(){
		if(mStateRepository == null){
			try {
				mStateRepository = new StateRepository(mConnectionSource);
				
				return mStateRepository;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return null;
	}

	public IForecastRepository ForecastRepository() {
		if(mForecastRepository == null){
			try {
				mForecastRepository = new ForecastRepository(mConnectionSource);
				
				return mForecastRepository;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}		
		
		return null;
	}

	public IReportingAreaRepository ReportingAreaRepository() {
		if(mReportingAreaRepository == null){
			try {
				mReportingAreaRepository = new ReportingAreaRepository(mConnectionSource);
				
				return mReportingAreaRepository;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return null;
	}
}
