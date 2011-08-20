package com.quadrictech.airqualitynow.presenter;

import java.util.List;

import roboguice.event.Observes;

import android.app.AlertDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import com.quadrictech.airqualitynow.R;
import com.quadrictech.airqualitynow.db.callback.ILocalRequestCallback;
import com.quadrictech.airqualitynow.event.BindedToServiceEvent;
import com.quadrictech.airqualitynow.model.ReportingArea;
import com.quadrictech.airqualitynow.presenter.util.ReportingAreaArrayAdapter;
import com.quadrictech.airqualitynow.presenter.util.IGuiRunnable;
import com.quadrictech.airqualitynow.service.helper.IDataProviderServiceHelper;
import com.quadrictech.airqualitynow.view.IReportingAreaListView;

public class ReportingAreaListPresenter implements IReportingAreaListPresenter<IReportingAreaListView<ListView>>{
	
	private IReportingAreaListView<ListView> mForecastListView;
	private Context mContext;
	private ReportingAreaArrayAdapter mAdapter;
	private List<ReportingArea> mReportingAreas;
	private IDataProviderServiceHelper mDataProviderServiceHelper;
	
	/***
	 * REquired for roboguice parameter injection
	 */
	public ReportingAreaListPresenter(){
		
	}
	
	public ReportingAreaListPresenter(IReportingAreaListView<ListView> view, IDataProviderServiceHelper dataProviderServiceHelper, Context context){
		mContext = context;
		mForecastListView = view;
		mDataProviderServiceHelper = dataProviderServiceHelper;
	}
	
	public void initialize(PresenterInitializeParameter parameterObject) {
		mContext = parameterObject.listView.getView().getContext();
		mForecastListView = parameterObject.listView;
		mDataProviderServiceHelper = parameterObject.dataProviderServiceHelper;
	}

	public void initializeList(@Observes BindedToServiceEvent event){
		if(mDataProviderServiceHelper != null){
			mDataProviderServiceHelper.getAllReportingAreas(new HandleGetReportingAreas());
		}
	}

	public void handleGetReportingAreas(ILocalRequestCallback<ReportingArea> callback){
		
		if(callback.getErrorStatus()){
			Toast.makeText(mContext, callback.getErrorMessage(), Toast.LENGTH_SHORT).show();			
		}
		else{
			mReportingAreas =  (List<ReportingArea>) callback.getList();
			mAdapter = new ReportingAreaArrayAdapter(mContext, R.layout.forecastlistrow, mReportingAreas);
			
			mForecastListView.setAdapter(mAdapter);
		}
	}	

	class HandleGetReportingAreas implements IGuiRunnable<ILocalRequestCallback<ReportingArea>>{
		ILocalRequestCallback<ReportingArea> callback;
		
		public void run() {
			handleGetReportingAreas(callback);			
		}

		@SuppressWarnings("unchecked")
		public void setCallback(ILocalRequestCallback<?> callback) {
			this.callback = (ILocalRequestCallback<ReportingArea>) callback;
		}
	}
	
	public void handleInsertReportingAreaCallback(ILocalRequestCallback<ReportingArea> callback){
		if(callback.getErrorStatus()){
			
		}
		else{
			ReportingArea area = callback.getList().get(0);
			int index = (mAdapter.getCount() == 0) ? 0:mAdapter.getCount() - 1;
			//mAdapter.insert(area, index);
		}
	}
	
	class HandleInsertReportingAreaCallback implements IGuiRunnable<ILocalRequestCallback<ReportingArea>>{
		ILocalRequestCallback<ReportingArea> callback;
		
		public void run() {
			// TODO Auto-generated method stub
		}

		@SuppressWarnings("unchecked")
		public void setCallback(ILocalRequestCallback<?> callback) {
			this.callback = (ILocalRequestCallback<ReportingArea>) callback;
		}
	}
	
	public void onDestroy() {
		
		if(mAdapter != null){
			mAdapter.clear();
			mAdapter = null;
		}
		
		if(mReportingAreas != null){
			mReportingAreas.clear();
			mReportingAreas = null;
		}
		
		mForecastListView.onDestroy();
		mForecastListView = null;
	}
	
	public void onPollutantGuideButtonClick() {
		AlertDialog.Builder builder;
		AlertDialog alertDialog;

		LayoutInflater inflater = (LayoutInflater) mContext.getSystemService("layout_inflater");
		View layout = inflater.inflate(R.layout.aqipollutantguide, null);

		builder = new AlertDialog.Builder(mContext);
		builder.setView(layout);
		alertDialog = builder.create();
		alertDialog.show();
	}

	public void onSearchAreaClick() {
		
	}

	public void onAddReportingAreaClick() {
		/*for(int i=0; i < mAdapter.getCount(); i++){
			if(mAdapter.getItem(i).ZipCode == mForecastListView.getEditTextString()){
				Toast.makeText(mContext, "found match", Toast.LENGTH_SHORT).show();
			}
		}*/
		Toast.makeText(mContext, this.mForecastListView.getEditTextString(), Toast.LENGTH_SHORT).show();
	}
}
