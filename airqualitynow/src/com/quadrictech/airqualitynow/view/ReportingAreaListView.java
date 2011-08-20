package com.quadrictech.airqualitynow.view;

import roboguice.inject.InjectView;

import com.google.inject.Inject;
import com.quadrictech.airqualitynow.R;
import com.quadrictech.airqualitynow.presenter.ReportingAreaListPresenter;
import com.quadrictech.airqualitynow.presenter.util.ForecastArrayAdapter;

import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.EditText;

public class ReportingAreaListView implements IReportingAreaListView<ListView>, OnItemClickListener, OnClickListener  {
	@InjectView(R.id.forecastAreasList) 				private ListView mView;
	@InjectView(R.id.forecastListTableSearchEditText) 	private EditText mSearchEditText;
	@InjectView(R.id.forecastListTableSearchButton)		private Button   mSearchButton;
	@InjectView(R.id.forecastListTableAddButton)		private Button   mAddButton;
	@InjectView(R.id.forecastListTableGuideButton)		private Button   mGuideButton;
	@Inject 											public ReportingAreaListPresenter mPresenter;
	
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
	}

	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
		// TODO Auto-generated method stub
		
	}

	public void onClick(View v) {
		if(v.getId() == R.id.forecastListTableSearchButton){
			
		}
		else if(v.getId() == R.id.forecastListTableAddButton){
			mPresenter.onAddReportingAreaClick();
		}
		else if(v.getId() == R.id.forecastListTableGuideButton){
			mPresenter.onPollutantGuideButtonClick();
		}
	}

	public void onDestroy() {
		mView = null;
	}

	public void setAdapter(ForecastArrayAdapter adapter) {
		mView.setAdapter(adapter);		
	}

	public String getEditTextString() {
		return this.mSearchEditText.getText().toString();
	}
}
