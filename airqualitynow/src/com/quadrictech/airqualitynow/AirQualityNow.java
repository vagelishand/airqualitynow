package com.quadrictech.airqualitynow;

import roboguice.activity.RoboActivity;
import android.os.Bundle;

public class AirQualityNow extends RoboActivity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
    }
}