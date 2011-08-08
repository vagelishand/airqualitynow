package com.quadrictech.airqualitynow.view;

import roboguice.inject.InjectView;

import com.google.inject.Inject;
import com.quadrictech.airqualitynow.R;
import com.quadrictech.airqualitynow.presenter.ForecastListPresenter;

import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.EditText;

public class ForecastListView implements IForecastListView<ListView>, OnItemClickListener, OnClickListener  {
	@InjectView(R.id.forecastAreasList) 			private ListView mView;
	@InjectView(R.id.mainTable1SearchEditText) 		private EditText mSearchEditText;
	@InjectView(R.id.mainTable1SearchButton)		private Button   mSearchButton;
	@InjectView(R.id.mainTable1AddButton)			private Button   mAddButton;
	@Inject 										public ForecastListPresenter mPresenter;
	
	public ForecastListView(){
		
	}
	
	public ListView getView() {
		return mView;
	}
	
	public void initialize() {
		((AdapterView<?>) mView).setOnItemClickListener(this);
		mSearchButton.setOnClickListener(this);
		mAddButton.setOnClickListener(this);
	}

	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
		// TODO Auto-generated method stub
		
	}

	public void onClick(View v) {
		if(v.getId() == R.id.mainTable1SearchButton){
			
		}
		else{
			
		}
	}

	public void onDestroy() {
		mView = null;
	}
}
