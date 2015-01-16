package com.devterous.aqn.view;

import java.util.ArrayList;
import java.util.List;
import com.devterous.aqn.R;
import com.devterous.aqn.model.Forecast;
import com.devterous.aqn.model.viewmodel.ForecastTodayTomorrow;
import com.devterous.aqn.presenter.IForecastPresenter;
import com.devterous.aqn.presenter.util.ForecastTodayTomorrowArrayAdapter;
import com.devterous.aqn.utils.AQIUtil;
import com.devterous.aqn.utils.ColorUtil;

import roboguice.inject.InjectView;
import android.content.Context;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.TextView;

public class ForecastView implements IForecastView<View>, OnClickListener, OnItemClickListener {
	@InjectView(R.id.forecastsList)									private ListView mListView;
	@InjectView(R.id.forecastTableReportingAreaTextView)			private TextView mAreaTextView;
	@InjectView(R.id.forecastTableTodayAQITextView)					private TextView mTodayAQITextView;
	@InjectView(R.id.forecastTableTodayPollutantNameTextView)		private TextView mTodayPollutantNameTextView;
	@InjectView(R.id.forecastTableTodayAQINameTextView)				private TextView mTodayAQINameTextView;
	@InjectView(R.id.forecastTableTodayMsgTextView)					private TextView mTodayMsgTextView;
	
	@InjectView(R.id.forecastTableTomorrowAQITextView)				private TextView mTomorrowAQITextView;
	@InjectView(R.id.forecastTableTomorrowMsgTextView)				private TextView mTomorrowMsgTextView;
	@InjectView(R.id.forecastTableTomorrowPollutantNameTextView)	private TextView mTomorrowPollutantNameTextView;
	@InjectView(R.id.forecastTableTomorrowAQINameTextView)			private TextView mTomorrowAQINameTextView;

	public IForecastPresenter<View> mPresenter;
	public String mReportingAreaName;
	private Context mContext;
	private List<ForecastTodayTomorrow> mForecastsTT;
	private ForecastTodayTomorrowArrayAdapter mAdapter;
	
	public void initialize() {
		
	}
	
	public void initialize(IForecastPresenter<View> presenter, String reportingAreaName) {
		mContext = mListView.getContext();
		mPresenter = presenter;
		mReportingAreaName = reportingAreaName;
		mForecastsTT = new ArrayList<ForecastTodayTomorrow>();
		mAreaTextView.setText(mReportingAreaName);
		mListView.setOnItemClickListener(this);
	}

	public View getView() {
		return mListView;
	}

	public void onDestroy() {
		// TODO Auto-generated method stub
		
	}

	public void onClick(View v) {
		// TODO Auto-generated method stub
		
	}

	public void setForecastTableValues(List<Forecast> forecasts) {
		if(forecasts == null || forecasts.size() == 0){
			return;
		}
		
		fillTwoDayForecastList(forecasts);
		
		if(mForecastsTT.size() > 0){
			sortTwoDayForecastList();
			setTodayForecastTableValues(mForecastsTT.get(0).TodayForecast);
			setTomorrowForecastTableValues(mForecastsTT.get(0).TomorrowForecast);
		}
		
		mAdapter = new ForecastTodayTomorrowArrayAdapter(mContext, R.id.forecastsList, mForecastsTT);
		setAdapter(mAdapter);
	}
	
	private void sortTwoDayForecastList(){
		int n;
		
		n = mForecastsTT.size();
		Forecast tmpForecast;
		
		for(int i = 0; i < n; i++){
			for(int j=1; j < n; j++){
				try{
					if(mForecastsTT.get(j-1).TodayForecast.AQI < mForecastsTT.get(j).TodayForecast.AQI){
						tmpForecast = mForecastsTT.get(j-1).TodayForecast;
						mForecastsTT.get(j-1).TodayForecast = mForecastsTT.get(j).TodayForecast; 
						mForecastsTT.get(j).TodayForecast = tmpForecast;
					}
					
					if(mForecastsTT.get(j-1).TomorrowForecast.AQI < mForecastsTT.get(j).TomorrowForecast.AQI){
						tmpForecast = mForecastsTT.get(j-1).TomorrowForecast;
						mForecastsTT.get(j-1).TomorrowForecast = mForecastsTT.get(j).TomorrowForecast; 
						mForecastsTT.get(j).TomorrowForecast = tmpForecast;
					}
				}
				catch(NullPointerException e){
					
				}
			}
		}
	}
	
	private void fillTwoDayForecastList(List<Forecast> forecasts){
		
		if(mForecastsTT == null){
			mForecastsTT = new ArrayList<ForecastTodayTomorrow>();
		}
				
		ForecastTodayTomorrow fTT;
		
		int size = forecasts.size();
		
		for(int i=0; i < size; i++){

			fTT = new ForecastTodayTomorrow();
			fTT.TodayForecast = forecasts.get(i);
			
			if((i + 1) < size){
				fTT.TomorrowForecast = forecasts.get(i+1);
				i++;
			}
			
			mForecastsTT.add(fTT);
		}
		
	}
	
	public void setAdapter(ForecastTodayTomorrowArrayAdapter adapter) {
		mListView.setAdapter(adapter);
	}

	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
		Forecast forecast = mAdapter.getItem(position).TodayForecast;
		
		setTodayForecastTableValues(forecast);
		
		forecast = mAdapter.getItem(position).TomorrowForecast;
		
		setTomorrowForecastTableValues(forecast);
	}

	public void setTodayForecastTableValues(Forecast forecast) {
		if(forecast == null){
			return;
		}
		
		if(forecast.AQI > -1){
			mTodayAQITextView.setText(forecast.AQI + "");		
			mTodayAQITextView.setBackgroundResource(ColorUtil.getAirQualityColor(forecast.AQI));
			mTodayAQINameTextView.setText(AQIUtil.getName(mContext, forecast.AQI));
			mTodayMsgTextView.setText(AQIUtil.getHealthMessage(mContext, forecast.AQI));
		}
		
		mTodayPollutantNameTextView.setText(forecast.Pollutant.FullName);
	}

	public void setTomorrowForecastTableValues(Forecast forecast) {
		if(forecast == null){
			return;
		}
		
		if(forecast.AQI > -1){
			mTomorrowAQITextView.setText(forecast.AQI + "");		
			mTomorrowAQITextView.setBackgroundResource(ColorUtil.getAirQualityColor(forecast.AQI));
			mTomorrowAQINameTextView.setText(AQIUtil.getName(mContext, forecast.AQI));
			mTomorrowMsgTextView.setText(AQIUtil.getHealthMessage(mContext, forecast.AQI));
		}
		
		mTomorrowPollutantNameTextView.setText(forecast.Pollutant.FullName);
	}
}
