package com.quadrictech.airqualitynow.presenter;

import java.util.List;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.quadrictech.airqualitynow.ForecastActivity;
import com.quadrictech.airqualitynow.ObservationActivity;
import com.quadrictech.airqualitynow.PollutantGuideActivity;
import com.quadrictech.airqualitynow.R;
import com.quadrictech.airqualitynow.ReportingAreaListActivity;
import com.quadrictech.airqualitynow.db.callback.IDataRequestCallback;
import com.quadrictech.airqualitynow.model.ReportingArea;
import com.quadrictech.airqualitynow.presenter.handlers.ReportingAreaGetAll;
import com.quadrictech.airqualitynow.presenter.handlers.ReportingAreaGetByZipCode;
import com.quadrictech.airqualitynow.presenter.handlers.ReportingAreaRemoteDownload;
import com.quadrictech.airqualitynow.presenter.util.ReportingAreaArrayAdapter;
import com.quadrictech.airqualitynow.service.helper.DataProviderServiceHelper;
import com.quadrictech.airqualitynow.service.helper.IDataProviderServiceHelper;
import com.quadrictech.airqualitynow.service.helper.RemoteDataProviderServiceHelper;
import com.quadrictech.airqualitynow.settings.AppPreferences;
import com.quadrictech.airqualitynow.settings.IPreferences;
import com.quadrictech.airqualitynow.view.IReportingAreaListView;

public class ReportingAreaListPresenter implements IReportingAreaListPresenter<IReportingAreaListView<ListView>>{
	
	private IReportingAreaListView<ListView> mForecastListView;
	public Context mContext;
	public ReportingAreaArrayAdapter mAdapter;
	private List<ReportingArea> mReportingAreas;
	private IDataProviderServiceHelper mDataProviderServiceHelper;
	public String mZipCode;
	public ReportingAreaListActivity mListActivity;
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
		mDataProviderServiceHelper = DataProviderServiceHelper.getInstance();
		DataProviderServiceHelper.getInstance().setWindowContext(mContext);
		initializeList();
	}

	public void initializeList(){
		mDataProviderServiceHelper.getAllReportingAreas(new ReportingAreaGetAll(this));
	}

	public void handleGetReportingAreas(IDataRequestCallback<ReportingArea> callback){
		
		if(callback.getErrorStatus()){
			Toast.makeText(mContext, callback.getErrorMessage(), Toast.LENGTH_SHORT).show();			
		}
		else{
			mReportingAreas =  (List<ReportingArea>) callback.getList();
			mAdapter = new ReportingAreaArrayAdapter(mContext, R.layout.reportingarealistrow, mReportingAreas);
			
			mForecastListView.setAdapter(mAdapter);
			
			if(callback.getList().size() == 0)
			{
				IPreferences pref = new AppPreferences(mContext);
				
				if(pref.getDefaultReportingAreaZipCode() != null){
					RemoteDataProviderServiceHelper.getInstance().
													getReportingAreaByZipCode(
															pref.getDefaultReportingAreaZipCode(), 
															new ReportingAreaRemoteDownload(this));
				}
			}
		}
	}	

	public void handleGetReportingAreaByZipCode(IDataRequestCallback<ReportingArea> callback){
		Toast.makeText(mContext, "function", Toast.LENGTH_SHORT).show();
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
		Intent intent = new Intent(mListActivity, PollutantGuideActivity.class);
		mListActivity.startActivity(intent);
	}

	public void onSearchAreaClick() {
		
	}

	public void onAddReportingAreaClick() {
		
			AlertDialog.Builder alert = new AlertDialog.Builder(mContext);
	
			alert.setTitle("Add Reporting Area");
			alert.setMessage("Enter Zip Code");
	
			// Set an EditText view to get user input 
			final EditText input = new EditText(mContext);
			input.setInputType(2);
			input.requestFocus();
			alert.setView(input);
			final ReportingAreaListPresenter presenter = this;
			alert.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int whichButton) {
				  mZipCode = input.getText().toString();

				for(int i=0; i < mAdapter.getCount(); i++){
					ReportingArea area = mAdapter.getItem(i);
					if(area.ZipCode.compareTo(mZipCode) == 0){
						Toast.makeText(mContext, mZipCode + " is reported via " + area.Name, Toast.LENGTH_SHORT).show();
						return;
					}
				}
			  mDataProviderServiceHelper.getReportingAreaByZipCode(mZipCode, new ReportingAreaGetByZipCode(presenter));
			  }
			});
	
			alert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
			  public void onClick(DialogInterface dialog, int whichButton) {
			    // Canceled.
			  }
			});
	
			alert.show();
	}

	public void onViewForecast(ReportingArea area) {
		Intent intent = new Intent(mListActivity, ForecastActivity.class);
		intent.putExtra("areaId", area.Id);
		intent.putExtra("areaName", area.Name);
		intent.putExtra("areaZipCode", area.ZipCode);
		mListActivity.startActivity(intent);
	}

	public void onViewObserved(ReportingArea area) {
		Intent intent = new Intent(mListActivity, ObservationActivity.class);
		intent.putExtra("areaId", area.Id);
		intent.putExtra("areaName", area.Name);
		intent.putExtra("areaZipCode", area.ZipCode);
		mListActivity.startActivity(intent);
	}
}
