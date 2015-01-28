package com.devterous.aqn.db;

import java.sql.SQLException;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.devterous.aqn.model.Category;
import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import com.devterous.aqn.model.Forecast;
import com.devterous.aqn.model.Observation;
import com.devterous.aqn.model.Pollutant;
import com.devterous.aqn.model.ReportingArea;

public class DatabaseHelper extends OrmLiteSqliteOpenHelper {

	private static final String DATABASE_NAME = "airquality.db";
	private static final int DATABASE_VERSION = 1;
	
	public DatabaseHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase sqlLiteDatabase, ConnectionSource connectionSource) {
		try {
			TableUtils.createTable(connectionSource, ReportingArea.class);
			TableUtils.createTable(connectionSource, Forecast.class);
			TableUtils.createTable(connectionSource, Observation.class);
			TableUtils.createTable(connectionSource, Pollutant.class);
			
			createPollutantData();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}

    private void createCategoryData() throws SQLException{
        Dao<Category, Integer> cDao = getDao(Category.class);

        Category c = new Category("Good");
        cDao.create(c);

        c = new Category("Moderate");
        cDao.create(c);

        c = new Category("Unhealthy for Sensitive Groups");
        cDao.create(c);

        c = new Category("Unhealthy");
        cDao.create(c);

        c = new Category("Very Unhealthy");
        cDao.create(c);

        c = new Category("Hazardous");
        cDao.create(c);

        c = new Category("Unavailable");
        cDao.create(c);
    }

	private void createPollutantData() throws SQLException{
		Dao<Pollutant, Integer> pDao = getDao(Pollutant.class);
		
		Pollutant p = new Pollutant();
		p.Name = "CO";
		p.FullName = "Carbon Monoxide";
		pDao.create(p);
		
		p = new Pollutant();
		p.Name = "NO2";
		p.FullName = "Nitrogen Dioxide";
		pDao.create(p);
		
		p = new Pollutant();
		p.Name = "OZONE";
		p.FullName = "Ozone";
		pDao.create(p);
		
		p = new Pollutant();
		p.Name = "PM10";
		p.FullName = "Particles(PM10)";
		pDao.create(p);
		
		p = new Pollutant();
		p.Name = "PM2.5";
		p.FullName = "Particles(PM2.5)";
		pDao.create(p);
		
		p = new Pollutant();
		p.Name = "SO2";
		p.FullName = "Sulfur Dioxide";
		pDao.create(p);
				
	}

	@Override
	public void onUpgrade(SQLiteDatabase arg0, ConnectionSource arg1, int arg2,
			int arg3) {
		// TODO Auto-generated method stub
		
	}
	
	private Dao<ReportingArea, Integer> reportingAreaDAO;
	
	public Dao<ReportingArea, Integer> getReportingAreaDAO() throws SQLException{
		if(reportingAreaDAO == null){
			reportingAreaDAO = getDao(ReportingArea.class);
		}
		
		return reportingAreaDAO;
	}
	
}
