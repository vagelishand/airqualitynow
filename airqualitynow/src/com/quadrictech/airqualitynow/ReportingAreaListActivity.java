package com.quadrictech.airqualitynow;

import com.google.inject.Inject;
import com.quadrictech.airqualitynow.R;
import com.quadrictech.airqualitynow.presenter.ReportingAreaListPresenter;
import com.quadrictech.airqualitynow.presenter.PresenterInitializeParameter;
import com.quadrictech.airqualitynow.service.helper.DataProviderServiceHelper;
import com.quadrictech.airqualitynow.view.ReportingAreaListView;

//import roboguice.activity.RoboActivity;
import roboguice.activity.RoboFragmentActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.View;

public class ReportingAreaListActivity extends RoboFragmentActivity {
	@Inject private ReportingAreaListView mReportingAreaListView;
	@Inject private ReportingAreaListPresenter mReportingAreaListPresenter;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.reportingarealist);
        
        DataProviderServiceHelper.getInstance().setWindowContext(this);
        
        mReportingAreaListView.initialize();
    	mReportingAreaListView.mPresenter = mReportingAreaListPresenter;
    	mReportingAreaListPresenter.mListActivity = this;
    	mReportingAreaListView.mListActivity = this; 
    	mReportingAreaListPresenter.initialize(new PresenterInitializeParameter(mReportingAreaListView));
    	registerForContextMenu(mReportingAreaListView.getView());
    }
    
    /**
	 * This is the menu that appears when a listItem is long clicked/pressed
	 */
    @Override
    public void onCreateContextMenu(ContextMenu menu, View view, ContextMenuInfo menuInfo) {
    	mReportingAreaListView.handleOnCreateContextMenu(menu, view, menuInfo);    	
    }
    
    @Override
    public void onResume(){
    	super.onResume();
    	DataProviderServiceHelper.getInstance().setWindowContext(this);
    }
    
    @Override
    public void onDestroy(){
    	mReportingAreaListView.onDestroy();
    	mReportingAreaListPresenter.onDestroy();
    	super.onDestroy();
    }
}