package com.quadrictech.airqualitynow.view;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.quadrictech.airqualitynow.R;
import com.quadrictech.airqualitynow.model.Forecast;
import com.quadrictech.airqualitynow.presenter.IForecastPresenter;
import com.quadrictech.airqualitynow.utils.ColorUtil;
import com.quadrictech.airqualitynow.utils.DateUtil;

import roboguice.inject.InjectView;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

public class ForecastView implements IForecastView<View>, OnClickListener {
	@InjectView(R.id.fourDayForecastLinearLayout)	private View mView;
	@InjectView(R.id.fourDayForecastTableAreaTextView)			TextView areaTextView;
	
	@InjectView(R.id.fourDayForecastTableDay1TextView)  		TextView day1TextView;
	@InjectView(R.id.fourDayForecastTableDay2TextView)  		TextView day2TextView;
	@InjectView(R.id.fourDayForecastTableDay3TextView)  		TextView day3TextView;
	@InjectView(R.id.fourDayForecastTableDay4TextView)  		TextView day4TextView;
	
	@InjectView(R.id.fourDayForecastTablePM101TextView)			TextView pm101TextView;
	@InjectView(R.id.fourDayForecastTablePM102TextView)			TextView pm102TextView;
	@InjectView(R.id.fourDayForecastTablePM103TextView)			TextView pm103TextView;
	@InjectView(R.id.fourDayForecastTablePM104TextView)			TextView pm104TextView;
	
	@InjectView(R.id.fourDayForecastTablePM251TextView)			TextView pm251TextView;
	@InjectView(R.id.fourDayForecastTablePM252TextView)			TextView pm252TextView;
	@InjectView(R.id.fourDayForecastTablePM253TextView)			TextView pm253TextView;
	@InjectView(R.id.fourDayForecastTablePM254TextView)			TextView pm254TextView;
	
	@InjectView(R.id.fourDayForecastTableOzone1TextView)		TextView ozone1TextView;
	@InjectView(R.id.fourDayForecastTableOzone2TextView)		TextView ozone2TextView;
	@InjectView(R.id.fourDayForecastTableOzone3TextView)		TextView ozone3TextView;
	@InjectView(R.id.fourDayForecastTableOzone4TextView)		TextView ozone4TextView;
	
	public IForecastPresenter<View> mPresenter;
	public String mReportingAreaName;
	
	public void initialize() {
		// TODO Auto-generated method stubthis.
	}
	
	public void initialize(IForecastPresenter<View> presenter, String reportingAreaName) {
		mPresenter = presenter;
		mReportingAreaName = reportingAreaName;
		areaTextView.setText(mReportingAreaName);
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
		
		//SimpleDateFormat sdf = new SimpleDateFormat("M/d");
		
		List<Forecast> pm10Forecasts = getForecastByParticle(forecasts, "PM10");
		List<Forecast> pm25Forecasts = getForecastByParticle(forecasts, "PM2.5");
		List<Forecast> ozoneForecasts = getForecastByParticle(forecasts, "OZONE");
		
		setPM10ForecastData(pm10Forecasts);
		setPM25ForecastData(pm25Forecasts);
		setOzoneForecastData(ozoneForecasts);
	}
	
	public void setForecastDates(Date initialDate){
		List<String> dates = DateUtil.getNextThreeMonthDayNumberDates(initialDate);
		
		day1TextView.setText(dates.get(0));
		day2TextView.setText(dates.get(1));
		day3TextView.setText(dates.get(2));
		day4TextView.setText(dates.get(3));
	}
	
	private void setPM10ForecastData(List<Forecast> forecasts){
		int aqi;
		
		if(forecasts.size() > 0){
			aqi = forecasts.get(0).AQI;
			pm101TextView.setText((forecasts.get(0).AQI == -1) ? "N/A" : aqi + "");
			pm101TextView.setBackgroundResource(ColorUtil.getAirQualityColor(aqi));
		}
		
		if(forecasts.size() > 1){
			aqi = forecasts.get(1).AQI;
			pm102TextView.setText((forecasts.get(1).AQI == -1) ? "N/A" : aqi + "");
			pm102TextView.setBackgroundResource(ColorUtil.getAirQualityColor(aqi));
		}
		
		if(forecasts.size() > 2){
			aqi = forecasts.get(2).AQI;
			pm103TextView.setText((forecasts.get(2).AQI == -1) ? "N/A" : forecasts.get(2).AQI + "");
			pm103TextView.setBackgroundResource(ColorUtil.getAirQualityColor(aqi));
		}		
		
		if(forecasts.size() > 3){
			aqi = forecasts.get(3).AQI;
			pm104TextView.setText((forecasts.get(3).AQI == -1) ? "N/A" : forecasts.get(3).AQI + "");
			pm104TextView.setBackgroundResource(ColorUtil.getAirQualityColor(aqi));
		}
	}

	private void setPM25ForecastData(List<Forecast> forecasts){
		int aqi;
		if(forecasts.size() > 0){
			aqi = forecasts.get(0).AQI;
			pm251TextView.setText((forecasts.get(0).AQI == -1) ? "N/A" : forecasts.get(0).AQI + "");
			pm251TextView.setBackgroundResource(ColorUtil.getAirQualityColor(aqi));
		}
		
		if(forecasts.size() > 1){
			aqi = forecasts.get(1).AQI;
			pm252TextView.setText((forecasts.get(1).AQI == -1) ? "N/A" : forecasts.get(1).AQI + "");
			pm252TextView.setBackgroundResource(ColorUtil.getAirQualityColor(aqi));
		}
		
		if(forecasts.size() > 2){
			aqi = forecasts.get(2).AQI;
			pm253TextView.setText((forecasts.get(2).AQI == -1) ? "N/A" : forecasts.get(2).AQI + "");
			pm253TextView.setBackgroundResource(ColorUtil.getAirQualityColor(aqi));
		}		
		
		if(forecasts.size() > 3){
			aqi = forecasts.get(3).AQI;
			pm254TextView.setText((forecasts.get(3).AQI == -1) ? "N/A" : forecasts.get(3).AQI + "");
			pm254TextView.setBackgroundResource(ColorUtil.getAirQualityColor(aqi));
		}
	}
	
	private void setOzoneForecastData(List<Forecast> forecasts){
		int aqi;
		
		if(forecasts.size() > 0){
			aqi = forecasts.get(0).AQI;
			ozone1TextView.setText((forecasts.get(0).AQI == -1) ? "N/A" : forecasts.get(0).AQI + "");
			ozone1TextView.setBackgroundResource(ColorUtil.getAirQualityColor(aqi));
		}
		
		if(forecasts.size() > 1){
			aqi = forecasts.get(1).AQI;
			ozone2TextView.setText((forecasts.get(1).AQI == -1) ? "N/A" : forecasts.get(1).AQI + "");
			ozone2TextView.setBackgroundResource(ColorUtil.getAirQualityColor(aqi));
		}
		
		if(forecasts.size() > 2){
			aqi = forecasts.get(2).AQI;
			ozone3TextView.setText((forecasts.get(2).AQI == -1) ? "N/A" : forecasts.get(2).AQI + "");
			ozone3TextView.setBackgroundResource(ColorUtil.getAirQualityColor(aqi));
		}		
		
		if(forecasts.size() > 3){
			aqi = forecasts.get(3).AQI;
			ozone4TextView.setText((forecasts.get(3).AQI == -1) ? "N/A" : forecasts.get(3).AQI + "");
			ozone4TextView.setBackgroundResource(ColorUtil.getAirQualityColor(aqi));
		}
	}	
	
	private List<Forecast> getForecastByParticle(List<Forecast> forecasts, String particle){
		List<Forecast> returnList = new ArrayList<Forecast>();
		
		for(Forecast f: forecasts){
			if(f.ParameterName.toUpperCase().compareTo(particle) == 0){
				returnList.add(f);
			}
		}
		
		return returnList;
	}
}


/**
 * 	@InjectView(R.id.observedTableCurrentLabelTextView)			public TextView currentTextView;
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

 * /
 */