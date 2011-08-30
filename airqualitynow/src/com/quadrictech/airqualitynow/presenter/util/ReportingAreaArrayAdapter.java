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
		TextView currentTextView;
		TextView forecastTextView;
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
			
			mViewHolder.currentTextView = (TextView)convertView.findViewById(R.id.reportingAreaListCurrentTextView);
			
			mViewHolder.forecastTextView = (TextView)convertView.findViewById(R.id.reportingAreaListForecastTextView);
			
			convertView.setTag(mViewHolder);
		}
		else{
			mViewHolder = (ViewHolder)convertView.getTag();
		}
		
		if(position % 2 == 0){
			mViewHolder.cityTextView.setBackgroundResource(R.drawable.container);
		}
		else{
			mViewHolder.cityTextView.setBackgroundResource(0);
		}
		
		mViewHolder.cityTextView.setText(((ReportingArea)this.getItem(position)).Name);
		int today = ((ReportingArea)this.getItem(position)).ObservedAQI;
		mViewHolder.currentTextView.setText((today == -1) ? "n/a" : today + "");
		mViewHolder.currentTextView.setBackgroundResource(ColorUtil.getAirQualityColor(today));
		
		int tomorrow = ((ReportingArea)this.getItem(position)).ForecastAQI;
		mViewHolder.forecastTextView.setText((tomorrow == -1) ? "n/a" :tomorrow + "");
		mViewHolder.forecastTextView.setBackgroundResource(ColorUtil.getAirQualityColor(tomorrow));
		
		return convertView;
	}
}
