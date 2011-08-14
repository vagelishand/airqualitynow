package com.quadrictech.airqualitynow.db;

import java.sql.SQLException;

import com.j256.ormlite.dao.BaseDaoImpl;
import com.j256.ormlite.support.ConnectionSource;


public class Repository<T> extends BaseDaoImpl<T, Integer>  {
	
	public Repository() throws SQLException{
		super(null);
	}
	
	public Repository(ConnectionSource connectionSource,
			Class<T> dataClass) throws SQLException {
		super(connectionSource, dataClass);
	}
	
}
