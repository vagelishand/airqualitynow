package com.quadrictech.airqualitynow.model;

import java.util.Date;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable
public class Forecast {
	@DatabaseField(id = true)
	public int id;
	@DatabaseField
	public Date DateIssue;
	@DatabaseField
	public String DateForecast;
	@DatabaseField
	public String ReportingArea;
	@DatabaseField
	public String StateCode;
	@DatabaseField
	public int Latitude;
	@DatabaseField
	public int Longitude;
	@DatabaseField
	public String ParameterName;
	@DatabaseField
	public int AQI; //-1 is no forecast
	@DatabaseField
	public int AQICurrent;
	@DatabaseField
	public int CategoryNumber;
	@DatabaseField
	public int CategoryName;
	@DatabaseField
	public boolean ActionDay;
	@DatabaseField
	public String Discussion;
}
