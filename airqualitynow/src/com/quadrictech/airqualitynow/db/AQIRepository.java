package com.quadrictech.airqualitynow.db;

import java.sql.SQLException;

import com.j256.ormlite.dao.BaseDaoImpl;
import com.j256.ormlite.support.ConnectionSource;


public class AQIRepository<T> extends BaseDaoImpl<T, Integer>  {

	public AQIRepository(ConnectionSource connectionSource,
			Class<T> dataClass) throws SQLException {
		super(connectionSource, dataClass);
	}
	
}
