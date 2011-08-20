package com.quadrictech.airqualitynow.model;

import java.util.Date;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable
public class Observed {
	public Observed(){}
	
	@DatabaseField(id=true)
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
	@DatabaseField
	public double Latitude;
	@DatabaseField
	public double Longitude;
	@DatabaseField
	public String ParameterName;
	@DatabaseField
	public int AQI;
	@DatabaseField
	public int CategoryNumber;
	@DatabaseField
	public String CategoryName;
}
