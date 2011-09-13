package com.quadrictech.airqualitynow;

import android.os.Bundle;
import roboguice.activity.RoboActivity;

public class PollutantGuideActivity extends RoboActivity {
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.aqipollutantguide);
	}
}
