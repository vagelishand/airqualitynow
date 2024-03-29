package com.devterous.aqn.settings;

public interface IPreferences {
	String getDefaultReportingArea();
	void setDefaultReportingArea(String string);
	int getDefaultReportingAreaId();
	void setDefaultReportingAreaId(int id);
	String getDefaultReportingAreaZipCode();
	void setDefaultReportingAreaZipCode(String zipCode);
	boolean getEulaAccepted();
	void setEulaAccepted();
	String getFacebookToken();
	void setFacebookToken(String token);
}
