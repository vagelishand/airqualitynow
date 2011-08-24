package com.quadrictech.airqualitynow.presenter.util;

import java.util.List;

import com.quadrictech.airqualitynow.R;
import com.quadrictech.airqualitynow.model.ReportingArea;
import com.quadrictech.airqualitynow.utils.ColorUtil;

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
			convertView = inflater.inflate(R.layout.reportingarealistrow, parent, false);
			
			mViewHolder = new ViewHolder();
			mViewHolder.cityTextView = (TextView)convertView.findViewById(R.id.reportingAreaListCityTextView);
			
			mViewHolder.todayTextView = (TextView)convertView.findViewById(R.id.reportingAreaListTodayTextView);
			
			mViewHolder.tomorrowTextView = (TextView)convertView.findViewById(R.id.reportingAreaListTomorrowTextView);
			
			convertView.setTag(mViewHolder);
		}
		else{
			mViewHolder = (ViewHolder)convertView.getTag();
		}
		
		mViewHolder.cityTextView.setText(((ReportingArea)this.getItem(position)).Name);
		int today = ((ReportingArea)this.getItem(position)).ObservedAQI;
		mViewHolder.todayTextView.setText(today + "");
		mViewHolder.todayTextView.setBackgroundResource(ColorUtil.getAirQualityColor(today));
		
		int tomorrow = ((ReportingArea)this.getItem(position)).ForecastAQI;
		mViewHolder.tomorrowTextView.setText(tomorrow + "");
		mViewHolder.tomorrowTextView.setBackgroundResource(ColorUtil.getAirQualityColor(tomorrow));
		
		return convertView;
	}
}
