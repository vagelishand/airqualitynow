package com.quadrictech.airqualitynow.db;

import java.sql.SQLException;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import com.quadrictech.airqualitynow.model.Forecast;
import com.quadrictech.airqualitynow.model.Observed;
import com.quadrictech.airqualitynow.model.ReportingArea;
import com.quadrictech.airqualitynow.model.State;

public class DatabaseHelper extends OrmLiteSqliteOpenHelper {

	private static final String DATABASE_NAME = "airquality.db";
	private static final int DATABASE_VERSION = 1;
	
	public DatabaseHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase sqlLiteDatabase, ConnectionSource connectionSource) {
		try {
			TableUtils.createTable(connectionSource, State.class);
			TableUtils.createTable(connectionSource, ReportingArea.class);
			TableUtils.createTable(connectionSource, Forecast.class);
			TableUtils.createTable(connectionSource, Observed.class);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}

	@Override
	public void onUpgrade(SQLiteDatabase arg0, ConnectionSource arg1, int arg2,
			int arg3) {
		// TODO Auto-generated method stub
		
	}
	
	private Dao<State, Integer> stateDAO;
	
	public Dao<State, Integer> getStateDAO() throws SQLException{
		if(stateDAO == null){
			stateDAO = getDao(State.class);
		}
		
		return stateDAO;
	}
	
	private Dao<ReportingArea, Integer> reportingAreaDAO;
	
	public Dao<ReportingArea, Integer> getReportingAreaDAO() throws SQLException{
		if(reportingAreaDAO == null){
			reportingAreaDAO = getDao(ReportingArea.class);
		}
		
		return reportingAreaDAO;
	}
	
}
