package com.devterous.aqn;

import android.os.Bundle;
import roboguice.activity.RoboActivity;
import com.devterous.aqn.R;

public class PollutantGuideActivity extends RoboActivity {
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.aqipollutantguide);
	}
}
