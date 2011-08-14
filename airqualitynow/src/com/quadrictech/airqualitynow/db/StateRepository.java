package com.quadrictech.airqualitynow.db;

import java.sql.SQLException;

import com.j256.ormlite.support.ConnectionSource;
import com.quadrictech.airqualitynow.model.State;

public class StateRepository extends Repository<State> implements IStateRepository{

	public StateRepository() throws SQLException{
		super();
	}
	
	public StateRepository(ConnectionSource connectionSource) throws SQLException {
		super(connectionSource, State.class);
		// TODO Auto-generated constructor stub
	}

}
