package com.quadrictech.airqualitynow;

import com.quadrictech.airqualitynow.location.ReverseGeoHelper;
import com.quadrictech.airqualitynow.service.DataProviderService;
import com.quadrictech.airqualitynow.service.RemoteDataProviderService;
import com.quadrictech.airqualitynow.service.helper.DataProviderServiceHelper;
import com.quadrictech.airqualitynow.service.helper.IDataProviderServiceHelper;
import com.quadrictech.airqualitynow.service.helper.IRemoteDataProviderServiceHelper;
import com.quadrictech.airqualitynow.service.helper.RemoteDataProviderServiceHelper;
import com.quadrictech.airqualitynow.settings.AppPreferences;
import com.quadrictech.airqualitynow.settings.IPreferences;

import roboguice.activity.RoboActivity;
import roboguice.inject.InjectView;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

import com.google.ads.*;

public class AirQualityNow extends RoboActivity implements OnClickListener{
    /** Called when the activity is first created. */
	@InjectView(R.id.mainTableObservedForecastButton) 	Button mButton;
	@InjectView(R.id.mainTableForecastListButton)		Button mFButton;
	@InjectView(R.id.mainTableMapPointsButton)			Button mPButton;
	@InjectView(R.id.adView) private AdView adView;
	private IDataProviderServiceHelper mDataProviderServiceHelper;
	private IRemoteDataProviderServiceHelper mRemoteDataProviderServiceHelper;
		
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        Eula.showEulaRequireAcceptance(this);
        
        checkForDefaultReportingArea();
        
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
        
        AdRequest adRequest = new AdRequest();
        adRequest.addTestDevice("17BAA6C5D06F6ABDD2DDED17A764AE35");
        // Initiate a generic request to load it with an ad
        adView.loadAd(new AdRequest());
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
    	
	    AlarmManager am = (AlarmManager) getSystemService(ALARM_SERVICE);
	    Intent i = new Intent(this, RemoteDataProviderService.class);
	    i.putExtra("FromAlarmManager", true);
	    PendingIntent pi = PendingIntent.getService(this, 0, i, 0);
	    am.cancel(pi);
    	    
	    am.setInexactRepeating(AlarmManager.ELAPSED_REALTIME_WAKEUP,
	    	    SystemClock.elapsedRealtime() + 1000*60,
	    	    AlarmManager.INTERVAL_DAY, pi);
    }
    
    @Override
    public void onDestroy(){
    	mDataProviderServiceHelper.doUnBindService();
    	mRemoteDataProviderServiceHelper.doUnBindService();
    	super.onDestroy();
    }

	public void onClick(View view) {
		if(view.getId() == R.id.mainTableObservedForecastButton){
			IPreferences pref = new AppPreferences(this);
			
			if(pref.getDefaultReportingAreaId() == 0){
				Toast.makeText(this, this.getString(R.string.defaultReportingAreaNotSet), Toast.LENGTH_SHORT).show();
				return;
			}

			Intent intent = new Intent(AirQualityNow.this, ObservationActivity.class);
			startActivity(intent);
		}
		else if(view.getId() == R.id.mainTableForecastListButton){
			Intent intent = new Intent(AirQualityNow.this, ReportingAreaListActivity.class);
			startActivity(intent);
		}
		else{
			Toast.makeText(this, "start", Toast.LENGTH_SHORT).show();
		}
			
	}
	
	public void checkForDefaultReportingArea(){
		IPreferences pref = new AppPreferences(this);
		
		if(pref.getDefaultReportingAreaZipCode() == null){
			ReverseGeoHelper.getInstance().getAddresses(getBaseContext());
		}
	}
	
}