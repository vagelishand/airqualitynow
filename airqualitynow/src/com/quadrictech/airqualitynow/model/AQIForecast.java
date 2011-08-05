package com.quadrictech.airqualitynow.model;

public class AQIForecast {
	public String DateIssue;
	public String DateForecast;
	public String ReportingArea;
	public String StateCode;
	public int Latitude;
	public int Longitude;
	public String ParameterName;
	public int AQI; //-1 is no forecast
	public int CategoryNumber;
	public int CategoryName;
	public boolean ActionDay;
	public String Discussion;
}
