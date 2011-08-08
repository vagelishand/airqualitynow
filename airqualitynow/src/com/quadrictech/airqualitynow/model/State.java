package com.quadrictech.airqualitynow.model;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable
public class State {
	@DatabaseField
	public int Id;
	@DatabaseField
	public String Name;
	
	public State(){
		
	}
}
