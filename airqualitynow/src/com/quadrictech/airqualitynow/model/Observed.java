package com.quadrictech.airqualitynow.model;

import java.util.Date;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable
public class Observed {
	public Observed(){}
	
	@DatabaseField(generatedId=true)
	public int Id;
	@DatabaseField
	public Date DateObserved;
	@DatabaseField
	public String HourObserved;
	@DatabaseField
	public String LocalTimeZone;
	@DatabaseField(canBeNull = false, foreign = true)
	public ReportingArea ReportingAreaObject;
	public String ReportingArea;
	public String StateCode;
	public double Latitude;
	public double Longitude;
	@DatabaseField(canBeNull = false, foreign = true, foreignAutoRefresh = true)
	public Pollutant Pollutant;
	public String ParameterName;
	@DatabaseField
	public int AQI;
	@DatabaseField
	public int CategoryNumber;
	public String CategoryName;
}
