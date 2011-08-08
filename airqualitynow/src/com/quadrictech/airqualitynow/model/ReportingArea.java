package com.quadrictech.airqualitynow.model;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable
public class ReportingArea {
	@DatabaseField	
	public String Name;
	@DatabaseField(index = true)
	public String State;
	@DatabaseField(index = true)
	public String ZipCode;
	
	public ReportingArea(){
		
	}
}
