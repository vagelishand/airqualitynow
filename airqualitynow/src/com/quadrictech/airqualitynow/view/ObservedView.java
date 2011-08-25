package com.quadrictech.airqualitynow.view;

import java.util.List;

import com.quadrictech.airqualitynow.R;
import com.quadrictech.airqualitynow.model.Observed;
import com.quadrictech.airqualitynow.presenter.IObservedPresenter;
import com.quadrictech.airqualitynow.utils.AQIUtil;
import com.quadrictech.airqualitynow.utils.ColorUtil;

import roboguice.inject.InjectView;
import android.content.Context;
import android.view.View;
import android.widget.TextView;

public class ObservedView implements IObservedView<View> {
	@InjectView(R.id.observedLinearLayout)						public View mView;
	
	@InjectView(R.id.observedTableReportingAreaTextView)		public TextView currentReportingAreaTextView;
	@InjectView(R.id.observedTableCurrentAQITimeDescTextView)	public TextView currentAQITimeDescTextView;
	@InjectView(R.id.observedTableCurrentAQITextView)			public TextView currentAQITextView;
	@InjectView(R.id.observedTableCurrentAQINameTextView)		public TextView currentAQINameTextView;
	@InjectView(R.id.observedTableCurrentAQIMsgTextView)		public TextView currentAQIMsgTextView;
	IObservedPresenter<View> mPresenter;

	private Context mContext;
	
	public ObservedView(){
		
	}
	
	public void initialize() {
		// TODO Auto-generated method stub
	}
	
	public void initialize(IObservedPresenter<View> presenter, String reportingAreaName) {
		mPresenter = presenter;
		currentReportingAreaTextView.setText(reportingAreaName);
		mContext = mView.getContext();
	}

	public View getView() {
		return mView;
	}

	public void onDestroy() {
		// TODO Auto-generated method stub

	}

	public void onClick(View v) {
		// TODO Auto-generated method stub

	}

	public void setObservedTableValues(List<Observed> observedList) {
		if(observedList.size() == 0){
			return;
		}
		
		Observed observed = observedList.get(0);
		currentAQITimeDescTextView.setText(observed.HourObserved);
		currentAQITextView.setText(observed.AQI + "");
		currentAQITextView.setBackgroundResource(ColorUtil.getAirQualityColor(observed.AQI));
		currentAQINameTextView.setText(AQIUtil.getName(mContext, observed.AQI));
		currentAQIMsgTextView.setText(AQIUtil.getHealthMessage(mContext, observed.AQI));
	}
}
