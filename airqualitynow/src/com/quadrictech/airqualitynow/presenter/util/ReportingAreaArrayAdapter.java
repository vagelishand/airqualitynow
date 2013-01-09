package com.quadrictech.airqualitynow.presenter.util;

import java.util.ArrayList;
import java.util.List;

import com.quadrictech.airqualitynow.R;
import com.quadrictech.airqualitynow.model.ReportingArea;
import com.quadrictech.airqualitynow.utils.ColorUtil;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

public class ReportingAreaArrayAdapter extends ArrayAdapter<ReportingArea> implements Filterable {
	static class ViewHolder{
		TextView cityTextView;
		TextView currentTextView;
		TextView forecastTextView;
	}
	
	ViewHolder mViewHolder;
	List<ReportingArea> mAreas;
	List<ReportingArea> mOriginalAreas;
	
	public ReportingAreaArrayAdapter(Context context, int layoutId,
			List<ReportingArea> areas) {
		super(context, layoutId);
		mAreas = areas;
		mOriginalAreas = areas;
	}
	
	@Override
	public void add(ReportingArea area){
		if(mAreas.size() == 0 && mOriginalAreas.size() > 0){
			mOriginalAreas.add(area);
		}

		mAreas.add(area);
		
		this.notifyDataSetChanged();
	}
	
	@Override
	public boolean isEmpty(){
		return mOriginalAreas == null && mOriginalAreas.size() > 0;
	}
	
	@Override
	public ReportingArea getItem(int index){
		return mAreas.get(index);
	}
	
	@Override
	public int getCount(){
		return mAreas.size();
	}
	
	@Override
	public void clear(){
		mAreas.clear();
		this.notifyDataSetChanged();
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
	
	@Override
	public Filter getFilter() {
	    return new Filter() {

	        @Override
	        protected FilterResults performFiltering(CharSequence constraint) {
	            final FilterResults oReturn = new FilterResults();
	            final List<ReportingArea> results = new ArrayList<ReportingArea>();
	            
	            if (mAreas == null)
	                mAreas = new ArrayList<ReportingArea>();
	            
	            if(constraint == null || constraint.toString().length() == 0){
	            	oReturn.count = mOriginalAreas.size();
	            	oReturn.values = mOriginalAreas;
	            }
	            else {
	                if (mAreas != null && mAreas.size() > 0) {
	                    for (final ReportingArea area : mAreas) {
	                        if (area.ZipCode.contains(constraint.toString())){
	                            results.add(area);
	                        }
	                    }
	                }

	                oReturn.count = results.size();
	                oReturn.values = results;
	            }
	            
	            return oReturn;
	        }

	        @SuppressWarnings("unchecked")
	        @Override
	        protected void publishResults(CharSequence constraint,
	                FilterResults results) {
	        	mAreas = (ArrayList<ReportingArea>) results.values;
	            notifyDataSetChanged();
	        }
	    };
	}

}
