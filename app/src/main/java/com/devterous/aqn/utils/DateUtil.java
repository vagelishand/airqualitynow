package com.devterous.aqn.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

public class DateUtil {
	//public static final String DATE_FORMAT = "yyyy/MM/dd HH:mm:ss a";
	/**
	 * Format yyyy/MM/dd HH:mm:ss a
	 */
	public static final String DATE_FORMAT = "M/dd/yyyy HH:mm:ss a";
	
	public static SimpleDateFormat getDateFormat(String dateFormat){
		return new SimpleDateFormat(dateFormat);
	}
	
	public static Date getDate(final String date, final String format) throws ParseException{
		return getDateFormat(format).parse(date);
	}
	
	public static String getDateString(final Date date, final String format) throws ParseException{
		SimpleDateFormat sdf = getDateFormat(format);
		return sdf.format(date);
	}
	
	public static Date getForecastIssueDate() throws ParseException{
		Date startingDate = new Date();
		GregorianCalendar gc = new GregorianCalendar();
		gc.setTime(startingDate);
		//gc.add(Calendar.DAY_OF_YEAR, -1);
		Date result = gc.getTime();		
		
		SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
		result = sdf.parse(sdf.format(result));
		
		return result;
	}
	
	public static Date getDateObserved() throws ParseException{
		Date now = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
		return sdf.parse(sdf.format(now));
	}
	
	public static List<String> getNextThreeMonthDayNumberDates(Date initialDate){
		List<String> dates = new ArrayList<String>();
		dates.add(getDateFormat("M/dd").format(initialDate));
		
		GregorianCalendar gc = new GregorianCalendar();
		gc.setTime(initialDate);
		gc.add(Calendar.DAY_OF_YEAR, 1);
		Date result = gc.getTime();
		dates.add(getDateFormat("M/dd").format(result));
		
		gc.setTime(result);
		gc.add(Calendar.DAY_OF_YEAR, 1);
		result = gc.getTime();
		dates.add(getDateFormat("M/dd").format(result));
		
		gc.setTime(result);
		gc.add(Calendar.DAY_OF_YEAR, 1);
		result = gc.getTime();
		dates.add(getDateFormat("M/dd").format(result));
		
		return dates;
	}
	/**
	 * String dateStr = "04/05/2010"; 
 
SimpleDateFormat curFormater = new SimpleDateFormat("dd/MM/yyyy"); 
Date dateObj = curFormater.parse(dateStr); 
SimpleDateFormat postFormater = new SimpleDateFormat("MMMM dd, yyyy"); 
 
String newDateStr = postFormater.format(dateObj)
	 */
	
}


