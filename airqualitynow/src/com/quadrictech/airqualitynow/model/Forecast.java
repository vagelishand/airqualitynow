package com.quadrictech.airqualitynow.model;

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
	@DatabaseField
	public Date DateForecast;
	@DatabaseField(canBeNull = false, foreign = true)
	public ReportingArea ReportingAreaObject;
	public String ReportingArea;
	public String StateCode;
	public double Latitude;
	public double Longitude;
	@DatabaseField
	public String ParameterName;
	@DatabaseField
	public int AQI; //-1 is no forecast
	@DatabaseField
	public int CategoryNumber;
	public String CategoryName;
	@DatabaseField
	public boolean ActionDay;
	@DatabaseField
	public String Discussion;
}
