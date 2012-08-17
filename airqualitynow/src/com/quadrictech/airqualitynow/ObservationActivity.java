package com.quadrictech.airqualitynow;

import com.google.inject.Inject;
import com.quadrictech.airqualitynow.presenter.IObservationPresenter;
import com.quadrictech.airqualitynow.presenter.PresenterInitializeParameter;
import com.quadrictech.airqualitynow.service.helper.DataProviderServiceHelper;
import com.quadrictech.airqualitynow.view.IObservationView;

import android.os.Bundle;
import android.widget.ListView;
import roboguice.activity.RoboActivity;

public class ObservationActivity extends RoboActivity {
	@Inject IObservationView<ListView> mObservedView;
	@Inject IObservationPresenter<ListView> mObservedPresenter;
	
	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.observed);
        
        DataProviderServiceHelper.getInstance().setWindowContext(this);
        
        mObservedView.initialize(mObservedPresenter, getIntent().getStringExtra("areaName"));
        mObservedPresenter.initialize(new PresenterInitializeParameter(mObservedView, getIntent().getIntExtra("areaId", 0),
        		                                                       getIntent().getStringExtra("areaZipCode")));
    }
    
    @Override
    public void onResume(){
    	super.onResume();
    	DataProviderServiceHelper.getInstance().setWindowContext(this);
    }
}
