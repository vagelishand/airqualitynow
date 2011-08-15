package com.quadrictech.airqualitynow.db;

import java.sql.SQLException;

import com.j256.ormlite.support.ConnectionSource;
import com.quadrictech.airqualitynow.model.Forecast;
import com.quadrictech.airqualitynow.model.ReportingArea;
import com.quadrictech.airqualitynow.model.State;

public class AppRepository implements IAppRepository {
	ConnectionSource mConnectionSource;
	Repository<Forecast> mForecastRepository;
	Repository<ReportingArea> mReportingAreaRepository;
	Repository<State> mStateRepository;
		
	public AppRepository(ConnectionSource connectionSource){
		mConnectionSource = connectionSource;
	}
	
	public Repository<State> StateRepository(){
		if(mStateRepository == null){
			try {
				mStateRepository = new Repository<State>(mConnectionSource, State.class);
				
				return mStateRepository;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return null;
	}

	public Repository<Forecast> ForecastRepository() {
		if(mForecastRepository == null){
			try {
				mForecastRepository = new Repository<Forecast>(mConnectionSource, Forecast.class);
				
				return mForecastRepository;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}		
		
		return null;
	}

	public Repository<ReportingArea> ReportingAreaRepository() {
		if(mReportingAreaRepository == null){
			try {
				mReportingAreaRepository = new Repository<ReportingArea>(mConnectionSource, ReportingArea.class);
				
				return mReportingAreaRepository;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return null;
	}
}
