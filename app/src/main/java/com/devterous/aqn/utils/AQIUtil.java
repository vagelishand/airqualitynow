package com.devterous.aqn.utils;

import android.content.Context;

import com.devterous.aqn.R;

public class AQIUtil {
	public static String getHealthMessage(Context context, int aqi){
		
		if(aqi > 0 && aqi <= 50)
			return context.getString(R.string.guideGoodMessage);
		else if(aqi > 50 && aqi <= 100)
			return context.getString(R.string.guideModerateMessage);
		else if(aqi > 100 && aqi <= 150)
			return context.getString(R.string.guideUSGMessage);
		else if(aqi > 150 && aqi <= 200)
			return context.getString(R.string.guideUnhealthyMessage);
		else if(aqi > 200 && aqi <= 300)
			return context.getString(R.string.guideVeryUnhealthyAlertMessage);
		
		return null;
	}
	
	public static String getName(Context context, int aqi){
		
		if(aqi > 0 && aqi <= 50)
			return context.getString(R.string.guideGoodName);
		else if(aqi > 50 && aqi <= 100)
			return context.getString(R.string.guideModerateName);
		else if(aqi > 100 && aqi <= 150)
			return context.getString(R.string.guideUSGName);
		else if(aqi > 150 && aqi <= 200)
			return context.getString(R.string.guideUnhealthyName);
		else if(aqi > 200 && aqi <= 300)
			return context.getString(R.string.guideVeryUnhealthyAlertName);
		
		return null;
	}
}
