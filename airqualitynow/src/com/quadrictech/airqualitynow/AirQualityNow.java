package com.quadrictech.airqualitynow;

import com.quadrictech.airqualitynow.forecast.AQIForecastActivity;
import com.quadrictech.airqualitynow.service.RemoteDataProviderService;

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
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        Intent intent = new Intent(this, RemoteDataProviderService.class);
        startService(intent);
        mButton.setOnClickListener(this);
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
	}
}