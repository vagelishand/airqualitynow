package com.devterous.aqn.presenter.util;

import java.text.ParseException;
import java.util.List;

import com.devterous.aqn.R;
import com.devterous.aqn.model.Observation;
import com.devterous.aqn.utils.ColorUtil;
import com.devterous.aqn.utils.DateUtil;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;


public class ObservedArrayAdapter extends ArrayAdapter<Observation> {
	static class ViewHolder{
		TextView pollutantDateTextView;
		TextView pollutantNameTextView;
		TextView pollutantAQITextView;
	}
	
	ViewHolder mViewHolder;
	
	public ObservedArrayAdapter(Context context, int textViewResourceId,
			List<Observation> observations) {
		super(context, textViewResourceId, observations);
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent){

		if(convertView == null){
			LayoutInflater inflater = (LayoutInflater) getContext().getSystemService("layout_inflater"); 
			convertView = inflater.inflate(R.layout.observedrow, parent, false);
			
			mViewHolder = new ViewHolder();
			
			mViewHolder.pollutantDateTextView = (TextView) convertView.findViewById(R.id.observationListPollutantDateTextView);
			mViewHolder.pollutantNameTextView = (TextView) convertView.findViewById(R.id.observationListPollutantNameTextView);
			mViewHolder.pollutantAQITextView = (TextView) convertView.findViewById(R.id.observationListPollutantAQITextView);
			
			convertView.setTag(mViewHolder);
		}
		else{
			mViewHolder = (ViewHolder)convertView.getTag();
		}
		
		Observation observation = getItem(position);
		
		try {
			mViewHolder.pollutantDateTextView.setText(DateUtil.getDateString(observation.DateObserved, "HH:mm a").replace("00:00 AM", "12:00 AM"));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		mViewHolder.pollutantNameTextView.setText(observation.Pollutant.FullName);
		mViewHolder.pollutantAQITextView.setText(observation.AQI + "");
		mViewHolder.pollutantAQITextView.setBackgroundResource(ColorUtil.getAirQualityColor(observation.AQI));
		
		return convertView;
	}	
	
}
