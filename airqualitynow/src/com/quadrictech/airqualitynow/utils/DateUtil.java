package com.quadrictech.airqualitynow.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {
	public static final String DATE_FORMAT = "yyyy/MM/dd HH:mm:ss a";
	
	public static SimpleDateFormat getDateFormat(String dateFormat){
		return new SimpleDateFormat(dateFormat);
	}
	
	public static Date getDate(String date, String format) throws ParseException{
		return getDateFormat(format).parse(date);
	}
	
	/**
	 * String dateStr = "04/05/2010"; 
 
SimpleDateFormat curFormater = new SimpleDateFormat("dd/MM/yyyy"); 
Date dateObj = curFormater.parse(dateStr); 
SimpleDateFormat postFormater = new SimpleDateFormat("MMMM dd, yyyy"); 
 
String newDateStr = postFormater.format(dateObj)
	 */
	
}


