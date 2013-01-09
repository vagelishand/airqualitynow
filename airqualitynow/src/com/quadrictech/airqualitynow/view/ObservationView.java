package com.quadrictech.airqualitynow.view;

import java.text.ParseException;

import com.facebook.android.Facebook;
import com.quadrictech.airqualitynow.ObservationActivity;
import com.quadrictech.airqualitynow.R;
import com.quadrictech.airqualitynow.facebook.Facebooker;
import com.quadrictech.airqualitynow.model.Observation;
import com.quadrictech.airqualitynow.presenter.IObservationPresenter;
import com.quadrictech.airqualitynow.presenter.util.ObservedArrayAdapter;
import com.quadrictech.airqualitynow.utils.AQIUtil;
import com.quadrictech.airqualitynow.utils.ColorUtil;
import com.quadrictech.airqualitynow.utils.DateUtil;

import roboguice.inject.InjectView;
import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.TextView;

public class ObservationView implements IObservationView<ListView>,OnItemClickListener, OnClickListener{
	@InjectView(R.id.observationList)						public ListView mListView;
	
	@InjectView(R.id.observedTableReportingAreaTextView)		public TextView currentReportingAreaTextView;
	@InjectView(R.id.observedTableCurrentAQITimeDescTextView)	public TextView currentAQITimeDescTextView;
	@InjectView(R.id.observedTableCurrentAQITextView)			public TextView currentAQITextView;
	@InjectView(R.id.observedTableCurrentAQINameTextView)		public TextView currentAQINameTextView;
	@InjectView(R.id.observedTableCurrentAQIMsgTextView)		public TextView currentAQIMsgTextView;
	@InjectView(R.id.observedTableObservationsTextView)			public TextView observationsTextView;
	IObservationPresenter<ListView> mPresenter;
	Facebook mFacebook;
		
	private ObservedArrayAdapter mAdapter;

	private Context mContext;
	
	public ObservationView(){
		
	}
	
	public void initialize() {
		// TODO Auto-generated method stub
	}
	
	public void initialize(IObservationPresenter<ListView> presenter, String reportingAreaName, Facebook facebook) {
		mPresenter = presenter;
		mFacebook = facebook;
		currentReportingAreaTextView.setText(reportingAreaName);
		mContext = mListView.getContext();
		mListView.setOnItemClickListener(this);
		observationsTextView.setOnClickListener(this);
	}

	public ListView getView() {
		return mListView;
	}

	public void onDestroy() {
		// TODO Auto-generated method stub

	}

	public void onClick(View v) {
		if(v.getId() == R.id.observedTableObservationsTextView){
			Facebooker face = new Facebooker((ObservationActivity)mContext, mFacebook, getFacebookDescription());
			face.share();
		}	                                                        
	}
	
	public String getFacebookDescription(){
		StringBuilder builder = new StringBuilder();
		
		builder.append(currentReportingAreaTextView.getText().toString() + "<center>  </center>");
		builder.append(currentAQITimeDescTextView.getText().toString()  + "<center>  </center>");
		Log.d("TEST", mAdapter.getCount() + "");
		for (int i=0; i < mAdapter.getCount(); i++){
			
			Observation o = (Observation)mAdapter.getItem(i);
			builder.append(o.Pollutant.FullName + " " + o.AQI + "<center>  </center>");
		}
		
		return builder.toString();
	}

	public void setObservationTableValues(Observation observation) throws ParseException {
		
		currentAQITimeDescTextView.setText(DateUtil.getDateString(observation.DateObserved, "M/dd/yyyy HH:mm a").replace("00:00 AM", "12:00 AM"));
		
		currentAQITextView.setText(observation.AQI + " " + AQIUtil.getName(mContext, observation.AQI));
		currentAQITextView.setBackgroundResource(ColorUtil.getAirQualityColor(observation.AQI));
		currentAQINameTextView.setText(observation.Pollutant.FullName);
		currentAQIMsgTextView.setText(AQIUtil.getHealthMessage(mContext, observation.AQI));
		
		observationsTextView.setText("Observations for " + DateUtil.getDateString(observation.DateObserved, "M/dd/yyyy"));
	}

	public void setAdapter(ObservedArrayAdapter observedArrayAdapter) {
		mAdapter = observedArrayAdapter;
		mListView.setAdapter(observedArrayAdapter);		
	}

	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
		Observation observation = mAdapter.getItem(position);
		try {
			setObservationTableValues(observation);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
