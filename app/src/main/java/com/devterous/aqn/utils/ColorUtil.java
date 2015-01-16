package com.devterous.aqn.utils;

import com.devterous.aqn.R;

public class ColorUtil {
	
	public static int getAirQualityColor(int aqi){
		
		if(aqi > 0 && aqi <= 50)
			return R.color.green;
		else if(aqi > 50 && aqi <= 100)
			return R.color.yellow;
		else if(aqi > 100 && aqi <= 150)
			return R.color.orange;
		else if(aqi > 150 && aqi <= 200)
			return R.color.red;
		else if(aqi > 200 && aqi <= 300)
			return R.color.maroon;
		
		return 0;
	}
}
