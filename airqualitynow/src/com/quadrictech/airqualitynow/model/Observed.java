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
	Date DateObserved;
	@DatabaseField
	int HourObserved;
	@DatabaseField
	String LocalTimeZone;
	//TODO reportingarea 
	@DatabaseField
	float Latitude;
	@DatabaseField
	float Longitude;
	@DatabaseField
	String ParamaterName;
	@DatabaseField
	int AQI;
	@DatabaseField
	int CategoryNumber;
	@DatabaseField
	String CategoryName;
	
}
