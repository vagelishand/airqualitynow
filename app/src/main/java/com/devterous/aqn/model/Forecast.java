package com.devterous.aqn.model;

import java.util.Date;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable
public class Forecast {
	public Forecast(){}
	
	@DatabaseField(generatedId = true)
	public int Id;
	@DatabaseField
	public Date DateIssue;
	@DatabaseField(uniqueCombo = true)
	public Date DateForecast;
	@DatabaseField(canBeNull = false, foreign = true, uniqueCombo = true)
	public ReportingArea ReportingAreaObject;
	public String ReportingArea;
	public String StateCode;
	public double Latitude;
	public double Longitude;
	@DatabaseField(canBeNull = false, foreign = true, foreignAutoRefresh = true, uniqueCombo = true)
	public Pollutant Pollutant;
	public String ParameterName;
	@DatabaseField
	public int AQI; //-1 is no forecast
    @DatabaseField(canBeNull = false, foreign = true, uniqueCombo = true)
    public Category Category;
	@DatabaseField
	public boolean ActionDay;
	@DatabaseField
	public String Discussion;
}
