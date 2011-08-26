package com.quadrictech.airqualitynow.presenter.util;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.quadrictech.airqualitynow.R;
import com.quadrictech.airqualitynow.model.viewmodel.ForecastTodayTomorrow;
import com.quadrictech.airqualitynow.utils.ColorUtil;

public class ForecastTodayTomorrowArrayAdapter extends ArrayAdapter<ForecastTodayTomorrow> {
	
	static class ViewHolder{
		TextView todayPollutantNameTextView;
		TextView todayAQITextView;
		TextView tomorrowPollutantNameTextView;
		TextView tomorrowAQITextView;
	}
	
	private ViewHolder mViewHolder;
	
	public ForecastTodayTomorrowArrayAdapter(Context context,
			int textViewResourceId, List<ForecastTodayTomorrow> forecasts) {
		super(context, textViewResourceId, forecasts);
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent){
		if(convertView == null){
			LayoutInflater inflater = (LayoutInflater) getContext().getSystemService("layout_inflater"); 
			convertView = inflater.inflate(R.layout.forecasttodaytomorrowrow, parent, false);
			
			mViewHolder = new ViewHolder();
			mViewHolder.todayAQITextView = (TextView)convertView.findViewById(R.id.forecastTodayTomorrowAQI1TextView);
			mViewHolder.todayPollutantNameTextView = (TextView)convertView.findViewById(R.id.forecastTodayTomorrowName1TextView);
			mViewHolder.tomorrowAQITextView = (TextView)convertView.findViewById(R.id.forecastTodayTomorrowAQI2TextView);
			mViewHolder.tomorrowPollutantNameTextView = (TextView)convertView.findViewById(R.id.forecastTodayTomorrowName2TextView);
			
			convertView.setTag(mViewHolder);
		}
		else{
			mViewHolder = (ViewHolder) convertView.getTag();
		}
		
		ForecastTodayTomorrow f = this.getItem(position);
		
		if(f.TodayForecast != null){
			mViewHolder.todayAQITextView.setText(f.TodayForecast.AQI + "");
			mViewHolder.todayAQITextView.setBackgroundResource(ColorUtil.getAirQualityColor(f.TodayForecast.AQI));
			mViewHolder.todayPollutantNameTextView.setText(f.TodayForecast.Pollutant.Name);
		}

		if(f.TomorrowForecast != null){
			mViewHolder.tomorrowAQITextView.setText(f.TomorrowForecast.AQI + "");
			mViewHolder.tomorrowAQITextView.setBackgroundResource(ColorUtil.getAirQualityColor(f.TomorrowForecast.AQI));
			mViewHolder.tomorrowPollutantNameTextView.setText(f.TomorrowForecast.Pollutant.Name);
		}
		
		return convertView;
	}
	
}