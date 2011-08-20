package com.quadrictech.airqualitynow.model;

import java.util.Date;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable
public class Forecast {
	public Forecast(){}
	
	@DatabaseField(id = true)
	public int Id;
	@DatabaseField
	public Date DateIssue;
	@DatabaseField
	public String DateForecast;
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
	public int AQI; //-1 is no forecast
	@DatabaseField
	public int CategoryNumber;
	@DatabaseField
	public String CategoryName;
	@DatabaseField
	public boolean ActionDay;
	@DatabaseField
	public String Discussion;
}
