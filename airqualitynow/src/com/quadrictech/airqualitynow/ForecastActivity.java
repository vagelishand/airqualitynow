package com.quadrictech.airqualitynow;

import com.google.inject.Inject;

import com.quadrictech.airqualitynow.R;
import com.quadrictech.airqualitynow.presenter.IForecastPresenter;
import com.quadrictech.airqualitynow.presenter.PresenterInitializeParameter;
import com.quadrictech.airqualitynow.service.helper.DataProviderServiceHelper;
import com.quadrictech.airqualitynow.view.IForecastView;

import roboguice.activity.RoboActivity;
import android.os.Bundle;
import android.view.View;

public class ForecastActivity extends RoboActivity {
	@Inject private IForecastView<View> mForecastView;
	@Inject private IForecastPresenter<View> mForecastPresenter;
	
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fourdayforecast);
                
        DataProviderServiceHelper.getInstance().setWindowContext(this);
        mForecastView.initialize(mForecastPresenter, getIntent().getStringExtra("areaName"));
        
    	mForecastPresenter.initialize(new PresenterInitializeParameter(mForecastView, 
    			                                                       getIntent().getIntExtra("areaId", 0)));
    }
    
    @Override
    public void onPause(){
    	super.onPause();
    }
    
    @Override
    public void onResume(){
    	super.onResume();
    	DataProviderServiceHelper.getInstance().setWindowContext(this);
    }
    
    @Override
    public void onDestroy(){
    	super.onDestroy();
    }
}