package com.quadrictech.airqualitynow.reportingarea;

import com.google.inject.Inject;
import com.quadrictech.airqualitynow.R;
import com.quadrictech.airqualitynow.presenter.ReportingAreaListPresenter;
import com.quadrictech.airqualitynow.presenter.PresenterInitializeParameter;
import com.quadrictech.airqualitynow.service.DataProviderService;
import com.quadrictech.airqualitynow.service.helper.DataProviderServiceHelper;
import com.quadrictech.airqualitynow.view.ReportingAreaListView;

import roboguice.activity.RoboActivity;
import roboguice.event.EventManager;
import android.content.Intent;
import android.os.Bundle;

public class ReportingAreaListActivity extends RoboActivity {
	@Inject private ReportingAreaListView mForecastListView;
	@Inject private ReportingAreaListPresenter mForecastListPresenter;
	@Inject protected EventManager mEventManager;
	private DataProviderServiceHelper serviceHelper;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.reportingarealist);
        
        Intent intent = new Intent(this, DataProviderService.class);
        startService(intent);
        serviceHelper = new DataProviderServiceHelper(mForecastListView.getView().getContext(), mEventManager);

        mForecastListView.initialize();
    	mForecastListView.mPresenter = mForecastListPresenter;
    	mForecastListPresenter.initialize(new PresenterInitializeParameter(mForecastListView,  mEventManager, serviceHelper));
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