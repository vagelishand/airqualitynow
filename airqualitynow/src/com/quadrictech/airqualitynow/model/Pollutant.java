package com.quadrictech.airqualitynow.model;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable
public class Pollutant {
	public Pollutant(){}
	
	@DatabaseField(generatedId = true)
	public int Id;
	@DatabaseField
	public String Name;
	@DatabaseField
	public String FullName;
}
