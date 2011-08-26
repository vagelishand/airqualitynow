package com.quadrictech.airqualitynow.view;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.quadrictech.airqualitynow.R;
import com.quadrictech.airqualitynow.model.Forecast;
import com.quadrictech.airqualitynow.model.viewmodel.ForecastTodayTomorrow;
import com.quadrictech.airqualitynow.presenter.IForecastPresenter;
import com.quadrictech.airqualitynow.presenter.util.ForecastTodayTomorrowArrayAdapter;
import com.quadrictech.airqualitynow.utils.AQIUtil;
import com.quadrictech.airqualitynow.utils.ColorUtil;

import roboguice.inject.InjectView;
import android.content.Context;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ListView;
import android.widget.TextView;

public class ForecastView implements IForecastView<View>, OnClickListener {
	@InjectView(R.id.forecastsList)						private ListView mView;
	@InjectView(R.id.forecastTableTodayAQITextView)				private TextView mTodayAQITextView;
	@InjectView(R.id.forecastTableTodayMsgTextView)				private TextView mTodayMsgTextView;
	
	@InjectView(R.id.forecastTableTomorrowAQITextView)			private TextView mTomorrowAQITextView;
	@InjectView(R.id.forecastTableTomorrowMsgTextView)			private TextView mTomorrowMsgTextView;

	/*@InjectView(R.id.forecastTableTodayParticle1AQITextView)	private TextView mTodayParticle1AQITextView;
	@InjectView(R.id.forecastTableTodayParticle2AQITextView)	private TextView mTodayParticle2AQITextView;
	@InjectView(R.id.forecastTableTodayParticle3AQITextView)	private TextView mTodayParticle3AQITextView;
	@InjectView(R.id.forecastTableTodayParticle4AQITextView)	private TextView mTodayParticle4AQITextView;
	@InjectView(R.id.forecastTableTodayParticle5AQITextView)	private TextView mTodayParticle5AQITextView;
	@InjectView(R.id.forecastTableTodayParticle6AQITextView)	private TextView mTodayParticle6AQITextView;
	
	@InjectView(R.id.forecastTableTodayParticle1NameTextView)	private TextView mTodayParticle1NameTextView;
	@InjectView(R.id.forecastTableTodayParticle2NameTextView)	private TextView mTodayParticle2NameTextView;
	@InjectView(R.id.forecastTableTodayParticle3NameTextView)	private TextView mTodayParticle3NameTextView;
	@InjectView(R.id.forecastTableTodayParticle4NameTextView)	private TextView mTodayParticle4NameTextView;
	@InjectView(R.id.forecastTableTodayParticle5NameTextView)	private TextView mTodayParticle5NameTextView;
	@InjectView(R.id.forecastTableTodayParticle6NameTextView)	private TextView mTodayParticle6NameTextView;
	
	@InjectView(R.id.forecastTableTomorrowParticle1AQITextView)	private TextView mTomorrowParticle1AQITextView;
	@InjectView(R.id.forecastTableTomorrowParticle2AQITextView)	private TextView mTomorrowParticle2AQITextView;
	@InjectView(R.id.forecastTableTomorrowParticle3AQITextView)	private TextView mTomorrowParticle3AQITextView;
	@InjectView(R.id.forecastTableTomorrowParticle4AQITextView)	private TextView mTomorrowParticle4AQITextView;
	@InjectView(R.id.forecastTableTomorrowParticle5AQITextView)	private TextView mTomorrowParticle5AQITextView;
	@InjectView(R.id.forecastTableTomorrowParticle6AQITextView)	private TextView mTomorrowParticle6AQITextView;
	
	@InjectView(R.id.forecastTableTomorrowParticle1NameTextView)	private TextView mTomorrowParticle1NameTextView;
	@InjectView(R.id.forecastTableTomorrowParticle2NameTextView)	private TextView mTomorrowParticle2NameTextView;
	@InjectView(R.id.forecastTableTomorrowParticle3NameTextView)	private TextView mTomorrowParticle3NameTextView;
	@InjectView(R.id.forecastTableTomorrowParticle4NameTextView)	private TextView mTomorrowParticle4NameTextView;
	@InjectView(R.id.forecastTableTomorrowParticle5NameTextView)	private TextView mTomorrowParticle5NameTextView;
	@InjectView(R.id.forecastTableTomorrowParticle6NameTextView)	private TextView mTomorrowParticle6NameTextView;*/	
	
	public IForecastPresenter<View> mPresenter;
	public String mReportingAreaName;
	private Context mContext;
	private List<ForecastTodayTomorrow> mForecastsTT;
	private ForecastTodayTomorrowArrayAdapter mAdapter;
	
	public void initialize() {
		// TODO Auto-generated method stubthis.
	}
	
	public void initialize(IForecastPresenter<View> presenter, String reportingAreaName) {
		mContext = mView.getContext();
		mPresenter = presenter;
		mReportingAreaName = reportingAreaName;
		mForecastsTT = new ArrayList<ForecastTodayTomorrow>();
		
		//areaTextView.setText(mReportingAreaName);
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

	public void setForecastTableValues(List<Forecast> forecasts) {
		if(forecasts.size() == 0){
			return;
		}
		Date filterDate = forecasts.get(0).DateForecast;
		
		List<Forecast> filteredForecasts = getDateFilteredList(filterDate, forecasts);
		
		for(Forecast f: filteredForecasts){
			ForecastTodayTomorrow fTT = new ForecastTodayTomorrow();
			fTT.TodayForecast = f;
			mForecastsTT.add(fTT);
		}
		
		setTodayTextViews(filteredForecasts);
		
		if(forecasts.size() > filteredForecasts.size()){
			filterDate = forecasts.get(filteredForecasts.size()).DateForecast;
			filteredForecasts = getDateFilteredList(filterDate, forecasts);
			
			for(int i = 0; i < filteredForecasts.size(); i++){
				if(i < mForecastsTT.size()){
					mForecastsTT.get(i).TomorrowForecast = filteredForecasts.get(i);
				}
			}
			
			setTomorrowTextViews(filteredForecasts);
		}
		
		mAdapter = new ForecastTodayTomorrowArrayAdapter(mContext, R.id.forecastsList, mForecastsTT);
		this.setAdapter(mAdapter);
	}
	
	private void setTodayTextViews(List<Forecast> filteredForecasts){
		int todayHiAQI = -1;
		
		//int cnt = 1;
		for(Forecast forecast: filteredForecasts){
			if(forecast.AQI > todayHiAQI){
				todayHiAQI = forecast.AQI;
			}
			
			/*switch(cnt){
				case 1:
					mTodayParticle1NameTextView.setText(forecast.Pollutant.Name);
					mTodayParticle1AQITextView.setText(forecast.AQI + "");
					mTodayParticle1AQITextView.setBackgroundResource(ColorUtil.getAirQualityColor(forecast.AQI));
				break;
				case 2:
					mTodayParticle2NameTextView.setText(forecast.Pollutant.Name);
					mTodayParticle2AQITextView.setText(forecast.AQI + "");
					mTodayParticle2AQITextView.setBackgroundResource(ColorUtil.getAirQualityColor(forecast.AQI));
				break;
				case 3:
					mTodayParticle3NameTextView.setText(forecast.Pollutant.Name);
					mTodayParticle3AQITextView.setText(forecast.AQI + "");
					mTodayParticle3AQITextView.setBackgroundResource(ColorUtil.getAirQualityColor(forecast.AQI));
				break;
				case 4:
					mTodayParticle4NameTextView.setText(forecast.Pollutant.Name);
					mTodayParticle4AQITextView.setText(forecast.AQI + "");
					mTodayParticle4AQITextView.setBackgroundResource(ColorUtil.getAirQualityColor(forecast.AQI));
				break;
				case 5:
					mTodayParticle5NameTextView.setText(forecast.Pollutant.Name);
					mTodayParticle5AQITextView.setText(forecast.AQI + "");
					mTodayParticle5AQITextView.setBackgroundResource(ColorUtil.getAirQualityColor(forecast.AQI));
				break;
				case 6:
					mTodayParticle6NameTextView.setText(forecast.Pollutant.Name);
					mTodayParticle6AQITextView.setText(forecast.AQI + "");
					mTodayParticle6AQITextView.setBackgroundResource(ColorUtil.getAirQualityColor(forecast.AQI));
				break;				
			}*/
			
			//cnt++;
		}
		
		mTodayAQITextView.setText(todayHiAQI + "");		
		mTodayAQITextView.setBackgroundResource(ColorUtil.getAirQualityColor(todayHiAQI));
		mTodayMsgTextView.setText(AQIUtil.getHealthMessage(mContext, todayHiAQI));
	}
	
	private void setTomorrowTextViews(List<Forecast> filteredForecasts){
		int tomorrowHiAQI = -1;
		
		//int cnt = 1;
		for(Forecast forecast: filteredForecasts){
			if(forecast.AQI > tomorrowHiAQI){
				tomorrowHiAQI = forecast.AQI;
			}
			
			/*switch(cnt){
				case 1:
					mTomorrowParticle1NameTextView.setText(forecast.Pollutant.Name);
					mTomorrowParticle1AQITextView.setText(forecast.AQI + "");
					mTomorrowParticle1AQITextView.setBackgroundResource(ColorUtil.getAirQualityColor(forecast.AQI));
				break;
				case 2:
					mTomorrowParticle2NameTextView.setText(forecast.Pollutant.Name);
					mTomorrowParticle2AQITextView.setText(forecast.AQI + "");
					mTomorrowParticle2AQITextView.setBackgroundResource(ColorUtil.getAirQualityColor(forecast.AQI));
				break;
				case 3:
					mTomorrowParticle3NameTextView.setText(forecast.Pollutant.Name);
					mTomorrowParticle3AQITextView.setText(forecast.AQI + "");
					mTomorrowParticle3AQITextView.setBackgroundResource(ColorUtil.getAirQualityColor(forecast.AQI));
				break;
				case 4:
					mTomorrowParticle4NameTextView.setText(forecast.Pollutant.Name);
					mTomorrowParticle4AQITextView.setText(forecast.AQI + "");
					mTomorrowParticle4AQITextView.setBackgroundResource(ColorUtil.getAirQualityColor(forecast.AQI));
				break;
				case 5:
					mTomorrowParticle5NameTextView.setText(forecast.Pollutant.Name);
					mTomorrowParticle5AQITextView.setText(forecast.AQI + "");
					mTomorrowParticle5AQITextView.setBackgroundResource(ColorUtil.getAirQualityColor(forecast.AQI));
				break;
				case 6:
					mTomorrowParticle6NameTextView.setText(forecast.Pollutant.Name);
					mTomorrowParticle6AQITextView.setText(forecast.AQI + "");
					mTomorrowParticle6AQITextView.setBackgroundResource(ColorUtil.getAirQualityColor(forecast.AQI));
				break;				
			}
			
			cnt++;*/
		}
		
		mTomorrowAQITextView.setText(tomorrowHiAQI + "");		
		mTomorrowAQITextView.setBackgroundResource(ColorUtil.getAirQualityColor(tomorrowHiAQI));
		mTomorrowMsgTextView.setText(AQIUtil.getHealthMessage(mContext, tomorrowHiAQI));
	}
	
	private List<Forecast> getDateFilteredList(Date date, List<Forecast> forecasts){
		List<Forecast> filteredForecasts = new ArrayList<Forecast>();
		
		for(Forecast f: forecasts){
			if(f.DateForecast.compareTo(date) == 0){
				filteredForecasts.add(f);
			}
		}
				
		
		return filteredForecasts;
	}

	public void setAdapter(ForecastTodayTomorrowArrayAdapter adapter) {
		mView.setAdapter(adapter);
	}
}
