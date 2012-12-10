package com.quadrictech.airqualitynow.settings;

public interface IPreferences {
	String getDefaultReportingArea();
	void setDefaultReportingArea(String string);
	int getDefaultReportingAreaId();
	void setDefaultReportingAreaId(int id);
	String getDefaultReportingAreaZipCode();
	void setDefaultReportingAreaZipCode(String zipCode);
}
