package com.devterous.aqn;

//import com.google.ads.AdRequest;
//import com.google.ads.AdView;
import com.google.inject.Inject;

import com.devterous.aqn.R;
import com.devterous.aqn.presenter.IForecastPresenter;
import com.devterous.aqn.presenter.PresenterInitializeParameter;
import com.devterous.aqn.service.helper.DataProviderServiceHelper;
import com.devterous.aqn.view.IForecastView;

import roboguice.activity.RoboActivity;
import roboguice.inject.InjectView;
import android.os.Bundle;
import android.view.View;

public class ForecastActivity extends RoboActivity {
	@Inject private IForecastView<View> mForecastView;
	@Inject private IForecastPresenter<View> mForecastPresenter;
	//@InjectView(R.id.adView) private AdView adView;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.forecast);
                
        DataProviderServiceHelper.getInstance().setWindowContext(this);
        mForecastView.initialize(mForecastPresenter, getIntent().getStringExtra("areaName"));
        
        //AdRequest adRequest = new AdRequest();
        //adRequest.addTestDevice("17BAA6C5D06F6ABDD2DDED17A764AE35");
        // Initiate a generic request to load it with an ad
        //adView.loadAd(new AdRequest());
        
    	mForecastPresenter.initialize(new PresenterInitializeParameter(mForecastView, 
    			                                                       getIntent().getIntExtra("areaId", 0),
    			                                                       getIntent().getStringExtra("areaZipCode")));
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