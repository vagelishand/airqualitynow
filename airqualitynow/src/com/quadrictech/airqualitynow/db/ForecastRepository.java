package com.quadrictech.airqualitynow.db;

import java.sql.SQLException;

import com.j256.ormlite.support.ConnectionSource;
import com.quadrictech.airqualitynow.model.Forecast;


public class ForecastRepository extends AQIRepository<Forecast> implements IForecastRepository{

	public ForecastRepository(ConnectionSource connectionSource,
			Class<Forecast> dataClass) throws SQLException {
		super(connectionSource, dataClass);
		// TODO Auto-generated constructor stub
	}

}
