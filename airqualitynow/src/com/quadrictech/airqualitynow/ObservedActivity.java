package com.quadrictech.airqualitynow;

import com.google.inject.Inject;
import com.quadrictech.airqualitynow.presenter.IObservedPresenter;
import com.quadrictech.airqualitynow.view.IObservedView;

import android.os.Bundle;
import android.view.View;
import roboguice.activity.RoboActivity;

public class ObservedActivity extends RoboActivity {
	@Inject IObservedView<View> mObservedView;
	@Inject IObservedPresenter<View> mObservedPresenter;
	
	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.observedwforecast);
        
        
    }
}