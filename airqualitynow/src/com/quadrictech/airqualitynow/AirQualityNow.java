package com.quadrictech.airqualitynow;

import com.quadrictech.airqualitynow.service.DataProviderService;
import com.quadrictech.airqualitynow.service.RemoteDataProviderService;
import com.quadrictech.airqualitynow.service.helper.DataProviderServiceHelper;
import com.quadrictech.airqualitynow.service.helper.IDataProviderServiceHelper;
import com.quadrictech.airqualitynow.service.helper.IRemoteDataProviderServiceHelper;
import com.quadrictech.airqualitynow.service.helper.RemoteDataProviderServiceHelper;

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
	private IDataProviderServiceHelper mDataProviderServiceHelper;
	private IRemoteDataProviderServiceHelper mRemoteDataProviderServiceHelper;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        Intent intent = new Intent(this, DataProviderService.class);
        startService(intent);
        mDataProviderServiceHelper = DataProviderServiceHelper.getInstance();
        mDataProviderServiceHelper.doBindService(this);
        
        intent = new Intent(this.getBaseContext(), RemoteDataProviderService.class);
        startService(intent);
        mRemoteDataProviderServiceHelper = RemoteDataProviderServiceHelper.getInstance();
        mRemoteDataProviderServiceHelper.doBindService(this);
        
        mButton.setOnClickListener(this);
        mFButton.setOnClickListener(this);
    }
    
    @Override
    public void onPause(){
    	mDataProviderServiceHelper.doUnBindService();
    	mRemoteDataProviderServiceHelper.doUnBindService();
    	super.onPause();
    }
    
    @Override
    public void onResume(){
    	super.onResume();
    	mDataProviderServiceHelper.doBindService(this);
    	mRemoteDataProviderServiceHelper.doBindService(this);
    }
    
    @Override
    public void onDestroy(){
    	mDataProviderServiceHelper.doUnBindService();
    	mRemoteDataProviderServiceHelper.doUnBindService();
    	super.onDestroy();
    }

	public void onClick(View view) {
		if(view.getId() == R.id.mainTableObservedForecastButton){
			Intent intent = new Intent(AirQualityNow.this, ObservedActivity.class);
			startActivity(intent);
		}
		else if(view.getId() == R.id.mainTableForecastListButton){
			Intent intent = new Intent(AirQualityNow.this, ReportingAreaListActivity.class);
			startActivity(intent);
		}
	}
}