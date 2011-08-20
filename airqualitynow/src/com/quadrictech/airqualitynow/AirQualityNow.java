package com.quadrictech.airqualitynow;

import com.quadrictech.airqualitynow.forecast.AQIForecastActivity;
import com.quadrictech.airqualitynow.reportingarea.ReportingAreaListActivity;

import roboguice.activity.RoboActivity;
import roboguice.inject.InjectView;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class AirQualityNow extends RoboActivity implements OnClickListener{
    /** Called when the activity is first created. */
	@InjectView(R.id.mainTableObservedForecastButton) Button mButton;
	@InjectView(R.id.mainTableForecastListButton)		Button mFButton;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        mButton.setOnClickListener(this);
        mFButton.setOnClickListener(this);
    }
    
    @Override
    public void onDestroy(){
    	super.onDestroy();
    }

	public void onClick(View view) {
		if(view.getId() == R.id.mainTableObservedForecastButton){
			Intent intent = new Intent(AirQualityNow.this, AQIForecastActivity.class);
			startActivity(intent);
		}
		else if(view.getId() == R.id.mainTableForecastListButton){
			Intent intent = new Intent(AirQualityNow.this, ReportingAreaListActivity.class);
			startActivity(intent);
		}
	}
}