package com.quadrictech.airqualitynow.test.db;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.lang.reflect.Field;

import com.quadrictech.airqualitynow.model.Pollutant;
import com.quadrictech.airqualitynow.utils.DateUtil;

public class ForecastDataHelper<T> {
	Class<T> clazz;
	
	public ForecastDataHelper(Class<T> clazz){
		this.clazz = clazz;
	}
	
	public List<T> getList() throws ParseException{
		
		List<T> list = new ArrayList<T>(){
			private static final long serialVersionUID = 1L;

		{
			add(getForecast(10, "OZONE", DateUtil.getDate("8/31/2011", "M/dd/yyyy")));
			add(getForecast(200, "PM10", DateUtil.getDate("8/31/2011", "M/dd/yyyy")));
			add(getForecast(200, "PM2.5", DateUtil.getDate("8/31/2011", "M/dd/yyyy")));
			add(getForecast(100, "OZONE", DateUtil.getDate("9/1/2011", "M/dd/yyyy")));
			add(getForecast(20, "PM10", DateUtil.getDate("9/1/2011", "M/dd/yyyy")));
			add(getForecast(25, "PM2.5", DateUtil.getDate("9/1/2011", "M/dd/yyyy")));
			add(getForecast(11, "OZONE", DateUtil.getDate("9/2/2011", "M/dd/yyyy")));
			add(getForecast(20, "PM10", DateUtil.getDate("9/2/2011", "M/dd/yyyy")));
			add(getForecast(29, "PM2.5", DateUtil.getDate("9/2/2011", "M/dd/yyyy")));
		}};
		
		return list;
	}
	
	private T getForecast(int aqi, String parameterName, Date forecastDate){
		T entity = null;
		try {
			entity = clazz.newInstance();
			Field field = clazz.getField("AQI");
			field.setInt(entity, aqi);
			field = clazz.getField("ParameterName");
			field.set(entity, parameterName);
			field = clazz.getField("DateForecast");
			field.set(entity, forecastDate);
			field = clazz.getField("Pollutant");
			Pollutant p = new Pollutant();
			p.Name = parameterName;
			field.set(entity, p);
			
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
