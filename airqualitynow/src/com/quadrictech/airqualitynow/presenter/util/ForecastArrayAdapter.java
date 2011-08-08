package com.quadrictech.airqualitynow.presenter.util;

import java.util.List;

import com.quadrictech.airqualitynow.R;
import com.quadrictech.airqualitynow.model.Forecast;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class ForecastArrayAdapter extends ArrayAdapter<Forecast> {
	static class ViewHolder{
		TextView cityTextView;
		TextView todayTextView;
		TextView tomorrowTextView;
	}
	
	ViewHolder mViewHolder;
	
	public ForecastArrayAdapter(Context context, int layoutId,
			List<Forecast> forecasts) {
		super(context, layoutId, forecasts);
		
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent){
		
		if(convertView == null){
			LayoutInflater inflater = (LayoutInflater) getContext().getSystemService("layout_inflater"); 
			convertView = inflater.inflate(R.layout.forecastlistrow, parent, false);
			
			mViewHolder = new ViewHolder();
			mViewHolder.cityTextView = (TextView)convertView.findViewById(R.id.forecastCityTextView);
			
			mViewHolder.todayTextView = (TextView)convertView.findViewById(R.id.forecastTodayTextView);
			
			mViewHolder.tomorrowTextView = (TextView)convertView.findViewById(R.id.forecastTomorrowTextView);
			
			convertView.setTag(mViewHolder);
		}
		else{
			mViewHolder = (ViewHolder)convertView.getTag();
		}
		
		mViewHolder.cityTextView.setText(((Forecast)this.getItem(position)).ReportingArea);
		mViewHolder.todayTextView.setText(((Forecast)this.getItem(position)).AQI + "");
		mViewHolder.tomorrowTextView.setText(((Forecast)this.getItem(position)).AQI + "");
		
		return convertView;
	}
}
