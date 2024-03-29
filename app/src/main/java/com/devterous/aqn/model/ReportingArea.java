package com.devterous.aqn.model;

import java.util.Date;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable
public class ReportingArea {
	@DatabaseField(generatedId = true)
	public int Id;
	@DatabaseField(unique = true)	
	public String Name;
	@DatabaseField
	public Date DateStamp;
	@DatabaseField
	public int ObservedAQI;
	@DatabaseField
	public int ForecastAQI;
	@DatabaseField
	public String State;
	@DatabaseField(index = true)
	public String ZipCode;
	@DatabaseField
	public int Latitude;
	@DatabaseField
	public int Longitude;
	
	public ReportingArea(){
		
	}
}
