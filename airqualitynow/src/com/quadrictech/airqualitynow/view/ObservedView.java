package com.quadrictech.airqualitynow.view;

import java.util.List;

import com.quadrictech.airqualitynow.R;
import com.quadrictech.airqualitynow.model.Forecast;
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
	@InjectView(R.id.forecastTableTodayAQITextView)				public TextView todayAQITextView;
	@InjectView(R.id.forecastTableTomorrowAQIDescTextView)		public TextView todayAQIDescTextView;
	@InjectView(R.id.forecastTableTomorrowAQITextView)			public TextView tomorrowAQITextView;
	@InjectView(R.id.forecastTableTomorrowAQIDescTextView)		public TextView tomorrowAQIDescTextView;
	@InjectView(R.id.forecastTableTodayMsgTextView)				public TextView todayMsgTextView;
	@InjectView(R.id.forecastTableTomorrowMsgTextView)			public TextView tomorrowMsgTextView;
	@InjectView(R.id.forecastTableTomorrowActionTextView)		public TextView tomorrowActionTextView;
	@InjectView(R.id.forecastTableTodayPollutantDetailTextView)	public TextView todayPollutantDetailTextView;
	@InjectView(R.id.forecastTableTomorrowPollutDetailTextView)	public TextView tomorrowPollutDetailTextView;
	@InjectView(R.id.forecastTableTodayPM25TextView)			public TextView todayPM25TextView;
	@InjectView(R.id.forecastTableTodayPM25AQITextView)			public TextView todayPM25AQITextView;
	@InjectView(R.id.forecastTableTodayPM25AQIDescTextView)		public TextView todayPM25AQIDescTextView;
	@InjectView(R.id.forecastTableTomorrowPM25TextView)			public TextView tomorrowPM25TextView;
	@InjectView(R.id.forecastTableTomorrowPM25AQITextView)		public TextView tomorrowPM25AQITextView;
	@InjectView(R.id.forecastTableTomorrowPM25AQIDescTextView)	public TextView tomorrowPM25AQIDescTextView;
	@InjectView(R.id.forecastTableTodayOzoneTextView)			public TextView todayOzoneTextView;
	@InjectView(R.id.forecastTableTodayOzoneAQITextView)		public TextView todayOzoneAQITextView;
	@InjectView(R.id.forecastTableTodayOzoneAQIDescTextView)	public TextView todayOzoneAQIDescTextView;
	@InjectView(R.id.forecastTableTomorrowOzoneTextView)		public TextView tomorrowOzoneTextView;
	@InjectView(R.id.forecastTableTomorrowOzoneAQITextView)		public TextView tomorrowOzoneAQITextView;
	@InjectView(R.id.forecastTableTomorrowOzoneAQIDescTextView)	public TextView tomorrowOzoneAQIDescTextView;
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

	public void setForecastTableValues(List<Forecast> forecasts) {
		if(forecasts.size() == 0){
			return;
		}		
		
		Forecast forecast = forecasts.get(0);
		
		todayAQITextView.setText(forecast.AQI + "");
		todayAQITextView.setBackgroundResource(ColorUtil.getAirQualityColor(forecast.AQI));
		todayMsgTextView.setText(AQIUtil.getHealthMessage(mContext, forecast.AQI));
		
		if(forecasts.size() > 1){
			tomorrowAQITextView.setText(forecasts.get(1).AQI + "");
		}
	}
	
}
