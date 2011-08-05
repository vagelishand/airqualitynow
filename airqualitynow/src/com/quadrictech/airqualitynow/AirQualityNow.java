package com.quadrictech.airqualitynow;

import com.google.inject.Inject;
import com.quadrictech.airqualitynow.forecast.AQIForecastView;

import roboguice.activity.RoboActivity;
import android.os.Bundle;

public class AirQualityNow extends RoboActivity {
	@Inject private AQIForecastView AirQualityNowView;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
    }
}