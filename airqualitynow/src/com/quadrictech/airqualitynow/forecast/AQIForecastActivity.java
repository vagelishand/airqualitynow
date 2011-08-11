package com.quadrictech.airqualitynow.forecast;

import com.google.inject.Inject;

import com.quadrictech.airqualitynow.R;
import com.quadrictech.airqualitynow.presenter.IForecastPresenter;
import com.quadrictech.airqualitynow.view.IForecastView;

import roboguice.activity.RoboActivity;
import android.os.Bundle;
import android.view.View;

public class AQIForecastActivity extends RoboActivity {
	@Inject private IForecastView<View> mForecastView;
	@Inject private IForecastPresenter<View> mForecastPresenter;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.forecast);
        
        mForecastView.initialize(mForecastPresenter);
    	mForecastPresenter.initialize(mForecastView);
    }
    
    @Override
    public void onDestroy(){
    	super.onDestroy();
    	mForecastView.onDestroy();
    	mForecastPresenter.onDestroy();
    }
}