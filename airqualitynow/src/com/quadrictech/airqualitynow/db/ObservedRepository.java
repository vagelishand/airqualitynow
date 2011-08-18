package com.quadrictech.airqualitynow.db;

import java.sql.SQLException;

import com.j256.ormlite.support.ConnectionSource;
import com.quadrictech.airqualitynow.model.Observed;

public class ObservedRepository extends GenericRepository<Observed> implements IObservedRepository{

	public ObservedRepository(ConnectionSource connectionSource) throws SQLException {
		super(connectionSource, Observed.class);
	}
}
