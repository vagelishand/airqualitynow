package com.devterous.aqn.db;

import java.sql.SQLException;

import com.j256.ormlite.support.ConnectionSource;
import com.devterous.aqn.model.Forecast;


public class ForecastRepository extends GenericRepository<Forecast> implements IForecastRepository{

	public ForecastRepository(ConnectionSource connectionSource) throws SQLException {
		super(connectionSource, Forecast.class);
		// TODO Auto-generated constructor stub
	}

}
