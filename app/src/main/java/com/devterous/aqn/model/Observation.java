package com.devterous.aqn.model;

import java.util.Date;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable
public class Observation {
	public Observation(){}
	
	@DatabaseField(generatedId=true)
	public int Id;
	@DatabaseField(uniqueCombo = true)
	public Date DateObserved;
	@DatabaseField(uniqueCombo = true)
	public int HourObserved;
	@DatabaseField
	public String LocalTimeZone;
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
	public int AQI;
	@DatabaseField(canBeNull = false, foreign = true, uniqueCombo = true)
	public Category Category;
}
