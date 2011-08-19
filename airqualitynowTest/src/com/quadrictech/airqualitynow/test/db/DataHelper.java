package com.quadrictech.airqualitynow.test.db;

import java.util.ArrayList;
import java.util.List;
import java.lang.reflect.Field;

public class DataHelper<T> {
	Class<T> clazz;
	
	public DataHelper(Class<T> clazz){
		this.clazz = clazz;
	}
	
	public List<T> getList(){
		
		List<T> list = new ArrayList<T>(){
			private static final long serialVersionUID = 1L;

		{
			add(getForecast(10));
			add(getForecast(200));
		}};
		
		return list;
	}
	
	private T getForecast(int aqi){
		T entity = null;
		try {
			entity = clazz.newInstance();
			Field field = clazz.getField("AQI");
			field.setInt(entity, aqi);
			
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchFieldException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return entity;
	}

}
