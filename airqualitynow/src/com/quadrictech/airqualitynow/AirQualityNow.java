package com.quadrictech.airqualitynow;

import com.google.inject.Inject;
import com.j256.ormlite.support.ConnectionSource;
import com.quadrictech.airqualitynow.db.DatabaseHelper;
import com.quadrictech.airqualitynow.presenter.ForecastListPresenter;
import com.quadrictech.airqualitynow.service.DataProviderService;
import com.quadrictech.airqualitynow.view.ForecastListView;

import roboguice.activity.RoboActivity;
import android.content.Intent;
import android.os.Bundle;

public class AirQualityNow extends RoboActivity {
	@Inject private ForecastListView mForecastListView;
	@Inject private ForecastListPresenter mForecastListPresenter;
	DatabaseHelper helper;
	private ConnectionSource mConnectionSource;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        Intent intent = new Intent(this, DataProviderService.class);
        startService(intent);
        helper = new DatabaseHelper(this);
        mConnectionSource = helper.getConnectionSource();
        initializeViews();
        initializePresenters();
    }
    
    private void initializeViews(){
    	mForecastListView.initialize();
    }
    
    private void initializePresenters(){
    	mForecastListView.mPresenter = mForecastListPresenter;
    	mForecastListPresenter.initialize(mForecastListView, mConnectionSource);
    }
    
    @Override
    public void onDestroy(){
    	super.onDestroy();
    	Intent intent = new Intent(this, DataProviderService.class);
    	stopService(intent);
    	mForecastListView.onDestroy();
    	mForecastListPresenter.onDestroy();
    }
}