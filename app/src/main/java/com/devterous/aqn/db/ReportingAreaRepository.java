package com.devterous.aqn.db;

import java.sql.SQLException;

import com.j256.ormlite.support.ConnectionSource;
import com.devterous.aqn.model.ReportingArea;

public class ReportingAreaRepository extends GenericRepository<ReportingArea> implements IReportingAreaRepository{

	
	public ReportingAreaRepository(ConnectionSource connectionSource) throws SQLException {
		super(connectionSource, ReportingArea.class);
		// TODO Auto-generated constructor stub
	}

}
