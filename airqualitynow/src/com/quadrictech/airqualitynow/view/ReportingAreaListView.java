package com.quadrictech.airqualitynow.view;

import roboguice.fragment.RoboFragment;
import roboguice.inject.InjectView;

import com.google.inject.Inject;
import com.quadrictech.airqualitynow.R;
import com.quadrictech.airqualitynow.ReportingAreaListActivity;
import com.quadrictech.airqualitynow.model.ReportingArea;
import com.quadrictech.airqualitynow.presenter.ReportingAreaListPresenter;
import com.quadrictech.airqualitynow.presenter.util.ReportingAreaArrayAdapter;
import com.quadrictech.airqualitynow.settings.AppPreferences;
import com.quadrictech.airqualitynow.settings.IPreferences;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MenuItem.OnMenuItemClickListener;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.EditText;

public class ReportingAreaListView extends RoboFragment implements IReportingAreaListView<ListView>, OnItemClickListener, OnClickListener, OnMenuItemClickListener  {
	@InjectView(R.id.reportingAreasList) 					private ListView mView;
	@InjectView(R.id.reportingAreaListTableSearchEditText) 	private EditText mSearchEditText;
	@InjectView(R.id.reportingAreaListTableSearchButton)	private Button   mSearchButton;
	@InjectView(R.id.reportingAreaListTableAddButton)		private Button   mAddButton;
	@InjectView(R.id.reportingAreaListTableGuideButton)		private Button   mGuideButton;
	@Inject 												public ReportingAreaListPresenter mPresenter;
	ReportingAreaArrayAdapter mReportingAreaArrayAdapter;
	
	public ReportingAreaListActivity mListActivity;
	
	public ReportingAreaListView(){
		
	}
	
	public ListView getView() {
		return mView;
	}
	
	public void initialize() {
		((AdapterView<?>) mView).setOnItemClickListener(this);
		mSearchButton.setOnClickListener(this);
		mAddButton.setOnClickListener(this);
		mGuideButton.setOnClickListener(this);
		mView.setTextFilterEnabled(true);
		mSearchEditText.addTextChangedListener(new TextWatcher(){

			public void afterTextChanged(Editable arg0) {
				// TODO Auto-generated method stub
				
			}

			public void beforeTextChanged(CharSequence arg0, int arg1,
					int arg2, int arg3) {
				// TODO Auto-generated method stub
				
			}

			public void onTextChanged(CharSequence s, int arg1, int arg2,
					int arg3) {
				mReportingAreaArrayAdapter.getFilter().filter(s);	
			}
			
		});
	}

	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
		// TODO Auto-generated method stub
		
	}

	public void onClick(View v) {
		if(v.getId() == R.id.reportingAreaListTableSearchButton){
			mReportingAreaArrayAdapter.getFilter().filter(mSearchEditText.getText().toString());
		}
		else if(v.getId() == R.id.reportingAreaListTableAddButton){
			mPresenter.onAddReportingAreaClick(this.mSearchEditText.getText().toString());
		}
		else if(v.getId() == R.id.reportingAreaListTableGuideButton){
			mPresenter.onPollutantGuideButtonClick();
		}
	}

	public void onDestroy() {
		mView = null;
	}

	public void setAdapter(ReportingAreaArrayAdapter adapter) {
		mReportingAreaArrayAdapter = adapter;
		mView.setAdapter(adapter);
	}

	public String getEditTextString() {
		return this.mSearchEditText.getText().toString();
	}

	public boolean onMenuItemClick(MenuItem item) {
		boolean returnValue = false;
		AdapterView.AdapterContextMenuInfo info;
		
		try {
            info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
       } catch (ClassCastException e) {
           return false;
       }
				
		ReportingArea area = (ReportingArea) mView.getAdapter().getItem(info.position);
		
		switch (item.getItemId()) {
	    	case Menu.FIRST: {
	    		mPresenter.onViewForecast(area);
	    		return returnValue = true;
	    		}
	    	case 2:{
	    		mPresenter.onViewObserved(area);
	    		return returnValue = true;
	    	}
	    	case 3:{
	    		IPreferences pref = new AppPreferences(mView.getContext());
	    		pref.setDefaultReportingAreaId(area.Id);
	    		pref.setDefaultReportingArea(area.Name);
	    		pref.setDefaultReportingAreaZipCode(area.ZipCode);
	    		
	    		Toast.makeText(mView.getContext(), area.Name + " " + mView.getContext().getString(R.string.defaultObserved), Toast.LENGTH_SHORT).show();
	    		
	    		return returnValue = true;	    		
	    	}
	    	case 4:{
	    		Toast.makeText(mView.getContext(), "three", Toast.LENGTH_SHORT).show();
	    		return returnValue = true;
	    	}

		}
	
		return returnValue;
	}

	public boolean onContextItemSelected(MenuItem item) {
		return false;
	}

	public void handleOnCreateContextMenu(ContextMenu menu, View view, ContextMenuInfo menuInfo) {
		AdapterView.AdapterContextMenuInfo info = null;
		
		try {
            info = (AdapterView.AdapterContextMenuInfo) menuInfo;
       } catch (ClassCastException e) {
          
       }
		
		ReportingArea area = (ReportingArea)mView.getAdapter().getItem(info.position);
		
		menu.setHeaderTitle(area.Name);
    	MenuItem forecastMenuItem = menu.add(0, Menu.FIRST, 0, mView.getContext().getString(R.string.forecasts));
    	forecastMenuItem.setOnMenuItemClickListener(this);
    	
    	MenuItem observedMenuItem = menu.add(0, 2, 0, mView.getContext().getString(R.string.observations));
    	observedMenuItem.setOnMenuItemClickListener(this);
    	
    	MenuItem deleteMenuItem = menu.add(0, 3, 0, mView.getContext().getString(R.string.defaultArea));
    	deleteMenuItem.setOnMenuItemClickListener(this);				
	}
}
