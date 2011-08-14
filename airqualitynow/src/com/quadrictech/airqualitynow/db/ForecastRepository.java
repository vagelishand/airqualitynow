package com.quadrictech.airqualitynow.db;

import java.sql.SQLException;

import com.j256.ormlite.support.ConnectionSource;
import com.quadrictech.airqualitynow.model.Forecast;


public class ForecastRepository extends Repository<Forecast> implements IForecastRepository{

	public ForecastRepository() throws SQLException{
		super();
	}
	
	public ForecastRepository(ConnectionSource connectionSource) throws SQLException {
		super(connectionSource, Forecast.class);
		// TODO Auto-generated constructor stub
	}

}
