package com.quadrictech.airqualitynow.utils;

import java.text.SimpleDateFormat;

public class DateUtil {
	public static final String DATE_FORMAT = "yyyy/MM/dd HH:mm:ss a";
	
	public static SimpleDateFormat getDateFormat(String dateFormat){
		return new SimpleDateFormat(dateFormat);
	}
}
