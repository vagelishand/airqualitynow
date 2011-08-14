package com.quadrictech.airqualitynow.db;

import java.sql.SQLException;

import com.j256.ormlite.support.ConnectionSource;
import com.quadrictech.airqualitynow.model.ReportingArea;

public class ReportingAreaRepository extends Repository<ReportingArea> implements IReportingAreaRepository{

	public ReportingAreaRepository() throws SQLException{
		super();
	}
	
	public ReportingAreaRepository(ConnectionSource connectionSource) throws SQLException {
		super(connectionSource, ReportingArea.class);
		// TODO Auto-generated constructor stub
	}

}
