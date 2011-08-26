package com.quadrictech.airqualitynow.db;

import java.sql.SQLException;

import com.j256.ormlite.support.ConnectionSource;
import com.quadrictech.airqualitynow.model.Observation;

public class ObservationRepository extends GenericRepository<Observation> implements IObservationRepository{

	public ObservationRepository(ConnectionSource connectionSource) throws SQLException {
		super(connectionSource, Observation.class);
	}
}
