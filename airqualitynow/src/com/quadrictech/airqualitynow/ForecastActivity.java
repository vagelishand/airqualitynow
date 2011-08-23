package com.quadrictech.airqualitynow;

import com.google.inject.Inject;

import com.quadrictech.airqualitynow.R;
import com.quadrictech.airqualitynow.presenter.IForecastPresenter;
import com.quadrictech.airqualitynow.presenter.PresenterInitializeParameter;
import com.quadrictech.airqualitynow.view.IForecastView;

import roboguice.activity.RoboActivity;
import roboguice.event.EventManager;
import android.os.Bundle;
import android.view.View;

public class ForecastActivity extends RoboActivity {
	@Inject private IForecastView<View> mForecastView;
	@Inject private IForecastPresenter<View> mForecastPresenter;
	@Inject private EventManager mEventManager; 
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fourdayforecast);
        
        mForecastView.initialize(mForecastPresenter);
    	mForecastPresenter.initialize(new PresenterInitializeParameter(mForecastView, mEventManager));
    }
    
    @Override
    public void onPause(){
    	super.onPause();
    }
    
    @Override
    public void onResume(){
    	super.onResume();
    }
    
    @Override
    public void onDestroy(){
    	super.onDestroy();
    	mForecastView.onDestroy();
    	mForecastPresenter.onDestroy();
    }
}