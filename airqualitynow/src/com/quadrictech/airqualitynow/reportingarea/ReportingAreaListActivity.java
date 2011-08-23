package com.quadrictech.airqualitynow.reportingarea;

import com.google.inject.Inject;
import com.quadrictech.airqualitynow.R;
import com.quadrictech.airqualitynow.presenter.ReportingAreaListPresenter;
import com.quadrictech.airqualitynow.presenter.PresenterInitializeParameter;
import com.quadrictech.airqualitynow.view.ReportingAreaListView;

import roboguice.activity.RoboActivity;
import roboguice.event.EventManager;
import android.os.Bundle;

public class ReportingAreaListActivity extends RoboActivity {
	@Inject private ReportingAreaListView mForecastListView;
	@Inject private ReportingAreaListPresenter mForecastListPresenter;
	@Inject protected EventManager mEventManager;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.reportingarealist);
        
        mForecastListView.initialize();
    	mForecastListView.mPresenter = mForecastListPresenter;
    }
    
    @Override
    public void onStart(){
    	mForecastListPresenter.initialize(new PresenterInitializeParameter(mForecastListView,  mEventManager));
    }
    
    @Override
    public void onDestroy(){
    	mForecastListView.onDestroy();
    	mForecastListPresenter.onDestroy();
    	super.onDestroy();
    }
}