package com.devterous.aqn.model;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable
public class Pollutant {
	public Pollutant(){}
	public Pollutant(int id, String name, String fullName){
		Id = id;
		Name = name;
		FullName = fullName;
	}
	
	@DatabaseField(generatedId = true)
	public int Id;
	@DatabaseField
	public String Name;
	@DatabaseField
	public String FullName;
}
