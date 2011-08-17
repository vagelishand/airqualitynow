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
	@DatabaseField
	public String ReportingArea;
	@DatabaseField
	public String StateCode;
	@DatabaseField
	public float Latitude;
	@DatabaseField
	public float Longitude;
	@DatabaseField
	public String ParameterName;
	@DatabaseField
	public int AQI;
	@DatabaseField
	public int CategoryNumber;
	@DatabaseField
	public String CategoryName;
	
}
