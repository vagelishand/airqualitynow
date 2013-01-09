package com.quadrictech.airqualitynow;

import com.facebook.android.Facebook;
import com.google.ads.AdRequest;
import com.google.ads.AdView;
import com.google.inject.Inject;
import com.quadrictech.airqualitynow.presenter.IObservationPresenter;
import com.quadrictech.airqualitynow.presenter.PresenterInitializeParameter;
import com.quadrictech.airqualitynow.service.helper.DataProviderServiceHelper;
import com.quadrictech.airqualitynow.settings.AppPreferences;
import com.quadrictech.airqualitynow.settings.IPreferences;
import com.quadrictech.airqualitynow.view.IObservationView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;
import roboguice.activity.RoboActivity;
import roboguice.inject.InjectView;

public class ObservationActivity extends RoboActivity {
	@Inject IObservationView<ListView> mObservedView;
	@Inject IObservationPresenter<ListView> mObservedPresenter;
	@InjectView(R.id.adView) private AdView adView;
	public Facebook mFacebook;
	
	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.observed);
        
        DataProviderServiceHelper.getInstance().setWindowContext(this);
        String areaName = getIntent().getStringExtra("areaName");
        int areaId = getIntent().getIntExtra("areaId", 0);
        
		if(areaName == null){
			IPreferences pref = new AppPreferences(this);
			
			areaName = pref.getDefaultReportingArea();
			areaId = pref.getDefaultReportingAreaId();
		}
		
		AdRequest adRequest = new AdRequest();
        adRequest.addTestDevice("17BAA6C5D06F6ABDD2DDED17A764AE35");
        // Initiate a generic request to load it with an ad
        adView.loadAd(new AdRequest());
        mFacebook = new Facebook("279821398695923");
        mObservedView.initialize(mObservedPresenter, areaName, mFacebook);
        mObservedPresenter.initialize(new PresenterInitializeParameter(mObservedView, areaId,
        		                                                       getIntent().getStringExtra("areaZipCode")));
    }
    
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        
        mFacebook.authorizeCallback(requestCode, resultCode, data);
    }
    
    @Override
    public void onResume(){
    	super.onResume();
    	DataProviderServiceHelper.getInstance().setWindowContext(this);
    }
}
