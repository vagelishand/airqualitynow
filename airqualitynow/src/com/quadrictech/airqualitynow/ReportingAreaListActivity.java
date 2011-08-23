package com.quadrictech.airqualitynow;

import com.google.inject.Inject;
import com.quadrictech.airqualitynow.R;
import com.quadrictech.airqualitynow.presenter.ReportingAreaListPresenter;
import com.quadrictech.airqualitynow.presenter.PresenterInitializeParameter;
import com.quadrictech.airqualitynow.view.ReportingAreaListView;

import roboguice.activity.RoboActivity;
import roboguice.event.EventManager;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class ReportingAreaListActivity extends RoboActivity {
	@Inject private ReportingAreaListView mReportingAreaListView;
	@Inject private ReportingAreaListPresenter mForecastListPresenter;
	@Inject protected EventManager mEventManager;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.reportingarealist);
        
        mReportingAreaListView.initialize();
    	mReportingAreaListView.mPresenter = mForecastListPresenter;
    	registerForContextMenu(mReportingAreaListView.getView());
    }
    
    @Override
    public void onStart(){
    	super.onStart();
    	mForecastListPresenter.mListActivity = this;
    	mForecastListPresenter.initialize(new PresenterInitializeParameter(mReportingAreaListView,  mEventManager));
    }
    
    /**
	 * This is the menu that appears when a listItem is long clicked/pressed
	 */
    @Override
    public void onCreateContextMenu(ContextMenu menu, View view, ContextMenuInfo menuInfo) {
    	
    	menu.setHeaderTitle("Menu");
    	MenuItem forecastMenuItem = menu.add(0, Menu.FIRST, 0, "Forecast");
    	forecastMenuItem.setOnMenuItemClickListener(mReportingAreaListView);
    	
    	MenuItem observedMenuItem = menu.add(0, 2, 0, "Observed");
    	observedMenuItem.setOnMenuItemClickListener(mReportingAreaListView);
    	
    	MenuItem deleteMenuItem = menu.add(0, 3, 0, "Delete");
    	deleteMenuItem.setOnMenuItemClickListener(mReportingAreaListView);
    }
    
    @Override
    public void onDestroy(){
    	mReportingAreaListView.onDestroy();
    	mForecastListPresenter.onDestroy();
    	super.onDestroy();
    }
}