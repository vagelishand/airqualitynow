package com.quadrictech.airqualitynow;

import com.google.inject.Inject;
import com.quadrictech.airqualitynow.forecast.AQIForecastView;

import roboguice.activity.RoboTabActivity;
import android.os.Bundle;

public class AirQualityNow extends RoboTabActivity {
	@Inject private AQIForecastView AirQualityNowView;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
    }
}