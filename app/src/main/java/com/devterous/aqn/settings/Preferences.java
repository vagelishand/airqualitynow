package com.devterous.aqn.settings;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public abstract class Preferences {
	SharedPreferences mPreferences;
	public static final String PREFS_NAME = "MyPrefsFile";
	
	public Preferences(Context context){
		mPreferences = context.getSharedPreferences(PREFS_NAME, 0); 
	}
}
