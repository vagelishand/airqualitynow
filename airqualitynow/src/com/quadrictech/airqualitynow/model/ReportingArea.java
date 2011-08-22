package com.quadrictech.airqualitynow.model;

import java.util.Date;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable
public class ReportingArea {
	@DatabaseField(id = true)
	public String Id;
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
	
	public ReportingArea(){
		
	}
}
