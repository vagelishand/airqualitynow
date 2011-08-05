package com.quadrictech.airqualitynow.forecast;

import android.os.Bundle;

import com.quadrictech.airqualitynow.R;

import roboguice.activity.RoboActivity;

public class AQIForecastActivity extends RoboActivity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.forecast);
    }

}
