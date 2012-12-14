package com.quadrictech.airqualitynow.settings;

import android.content.Context;
import android.content.SharedPreferences;

public class AppPreferences extends Preferences implements IPreferences {

	 SharedPreferences.Editor mEditor;
	
	public AppPreferences(Context context) {
		super(context);
		mEditor = this.mPreferences.edit();
	}

	public String getDefaultReportingArea() {
		return mPreferences.getString("reportingArea", null);
	}

	public void setDefaultReportingArea(String reportingArea) {
		mEditor.putString("reportingArea", reportingArea);
		mEditor.commit();
	}

	public int getDefaultReportingAreaId() {
		return mPreferences.getInt("reportingAreaId", 0);
	}

	public void setDefaultReportingAreaId(int id) {
		mEditor.putInt("reportingAreaId", id);
		mEditor.commit();
	}

	public String getDefaultReportingAreaZipCode() {
		return mPreferences.getString("reportingAreaZip", null);
	}

	public void setDefaultReportingAreaZipCode(String zipCode) {
		mEditor.putString("reportingAreaZip", zipCode);
		mEditor.commit();
	}

	public boolean getEulaAccepted() {
		return mPreferences.getBoolean("eula", false);
	}

	public void setEulaAccepted() {
		mEditor.putBoolean("eula", true);
	}
}
