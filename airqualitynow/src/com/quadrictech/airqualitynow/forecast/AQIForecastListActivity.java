package com.quadrictech.airqualitynow.forecast;

import com.google.inject.Inject;
import com.j256.ormlite.support.ConnectionSource;
import com.quadrictech.airqualitynow.R;
import com.quadrictech.airqualitynow.db.DatabaseHelper;
import com.quadrictech.airqualitynow.presenter.ForecastListPresenter;
import com.quadrictech.airqualitynow.presenter.PresenterInitializeParameter;
import com.quadrictech.airqualitynow.service.DataProviderService;
import com.quadrictech.airqualitynow.service.helper.DataProviderServiceHelper;
import com.quadrictech.airqualitynow.service.helper.IDataProviderServiceHelper;
import com.quadrictech.airqualitynow.view.ForecastListView;

import roboguice.activity.RoboActivity;
import roboguice.event.EventManager;
import android.content.Intent;
import android.os.Bundle;

public class AQIForecastListActivity extends RoboActivity {
	@Inject private ForecastListView mForecastListView;
	@Inject private ForecastListPresenter mForecastListPresenter;
	@Inject protected EventManager mEventManager;
	DatabaseHelper helper;
	private ConnectionSource mConnectionSource;
	private IDataProviderServiceHelper serviceHelper;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.forecastlist);
        
        Intent intent = new Intent(this.getApplicationContext(), DataProviderService.class);
        startService(intent);
        serviceHelper = new DataProviderServiceHelper(this.getApplicationContext(), mEventManager);
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
    	mForecastListPresenter.initialize(new PresenterInitializeParameter(mForecastListView, mConnectionSource, mEventManager, (DataProviderServiceHelper) this.serviceHelper));
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