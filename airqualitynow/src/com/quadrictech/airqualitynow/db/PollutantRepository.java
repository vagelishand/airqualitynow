package com.quadrictech.airqualitynow.db;

import java.sql.SQLException;
import com.j256.ormlite.support.ConnectionSource;
import com.quadrictech.airqualitynow.model.Pollutant;

public class PollutantRepository extends GenericRepository<Pollutant> implements
		IPollutantRepository {

	public PollutantRepository(ConnectionSource connectionSource) throws SQLException {
		super(connectionSource, Pollutant.class);
		
	}

	

}
