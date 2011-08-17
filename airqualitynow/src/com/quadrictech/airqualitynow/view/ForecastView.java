package com.quadrictech.airqualitynow.view;

import java.util.List;

import com.quadrictech.airqualitynow.R;
import com.quadrictech.airqualitynow.model.Forecast;
import com.quadrictech.airqualitynow.model.Observed;
import com.quadrictech.airqualitynow.presenter.IForecastPresenter;

import roboguice.inject.InjectView;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

public class ForecastView implements IForecastView<View>, OnClickListener {
	@InjectView(R.id.forecastLinearLayout)	private View mView;
	@InjectView(R.id.observedTableCurrentLabelTextView)			public TextView currentTextView;
	@InjectView(R.id.observedTableCurrentAQITimeDescTextView)	public TextView currentAQITimeDescTextView;
	@InjectView(R.id.observedTableCurrentAQITextView)			public TextView currentAQITextView;
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
	public IForecastPresenter<View> mPresenter;
	
	public void initialize() {
		// TODO Auto-generated method stubthis.
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

	public void setForecastTableValues(List<Forecast> forecast) {
		tomorrowAQITextView.setText(forecast.get(1).AQI + "");
		tomorrowAQIDescTextView.setText("");
		tomorrowMsgTextView.setText(forecast.get(1).Discussion);
		
	}

	public void initialize(IForecastPresenter<View> presenter) {
		mPresenter = presenter;		
	}

	public void setObservedTableValues(List<Observed> observedList) {
		currentTextView.setText("Current Conditions: " + observedList.get(0).ReportingArea);
		currentAQITextView.setText(observedList.get(0).AQI + "");//observed
	}

}
