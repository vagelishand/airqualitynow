package com.quadrictech.airqualitynow.presenter.util;

import java.util.List;

import com.quadrictech.airqualitynow.R;
import com.quadrictech.airqualitynow.model.ReportingArea;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class ReportingAreaArrayAdapter extends ArrayAdapter<ReportingArea> {
	static class ViewHolder{
		TextView cityTextView;
		TextView todayTextView;
		TextView tomorrowTextView;
	}
	
	ViewHolder mViewHolder;
	
	public ReportingAreaArrayAdapter(Context context, int layoutId,
			List<ReportingArea> areas) {
		super(context, layoutId, areas);
		
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent){
		
		if(convertView == null){
			LayoutInflater inflater = (LayoutInflater) getContext().getSystemService("layout_inflater"); 
			convertView = inflater.inflate(R.layout.forecastlistrow, parent, false);
			
			mViewHolder = new ViewHolder();
			mViewHolder.cityTextView = (TextView)convertView.findViewById(R.id.forecastListCityTextView);
			
			mViewHolder.todayTextView = (TextView)convertView.findViewById(R.id.forecastListTodayTextView);
			
			mViewHolder.tomorrowTextView = (TextView)convertView.findViewById(R.id.forecastListTomorrowTextView);
			
			convertView.setTag(mViewHolder);
		}
		else{
			mViewHolder = (ViewHolder)convertView.getTag();
		}
		
		//mViewHolder.cityTextView.setText(((ReportingArea)this.getItem(position)).ReportingArea);
		int today = ((ReportingArea)this.getItem(position)).ObservedAQI;
		mViewHolder.todayTextView.setText(today + "");
		mViewHolder.todayTextView.setBackgroundResource(getAirQualityColor(today));
		
		int tomorrow = ((ReportingArea)this.getItem(position)).ForecastAQI;
		mViewHolder.tomorrowTextView.setText(tomorrow + "");
		mViewHolder.tomorrowTextView.setBackgroundResource(getAirQualityColor(tomorrow));
		
		return convertView;
	}
	
	private int getAirQualityColor(int aqi){
		
		if(aqi > 0 && aqi <= 50)
			return R.color.green;
		else if(aqi > 50 && aqi <= 100)
			return R.color.yellow;
		else if(aqi > 100 && aqi <= 150)
			return R.color.orange;
		else if(aqi > 150 && aqi <= 200)
			return R.color.red;
		else if(aqi > 200 && aqi <= 300)
			return R.color.maroon;
		
		return 0;
	}
}
