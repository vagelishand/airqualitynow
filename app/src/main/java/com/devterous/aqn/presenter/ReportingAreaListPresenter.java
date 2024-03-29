package com.devterous.aqn.presenter;

import java.util.List;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.devterous.aqn.ForecastActivity;
import com.devterous.aqn.ObservationActivity;
import com.devterous.aqn.PollutantGuideActivity;
import com.devterous.aqn.R;
import com.devterous.aqn.ReportingAreaListActivity;
import com.devterous.aqn.db.callback.IDataRequestCallback;
import com.devterous.aqn.model.ReportingArea;
import com.devterous.aqn.presenter.handlers.ReportingAreaGetAll;
import com.devterous.aqn.presenter.handlers.ReportingAreaGetByZipCode;
import com.devterous.aqn.presenter.handlers.ReportingAreaRemoteDownload;
import com.devterous.aqn.presenter.util.ReportingAreaArrayAdapter;
import com.devterous.aqn.service.helper.DataProviderServiceHelper;
import com.devterous.aqn.service.helper.IDataProviderServiceHelper;
import com.devterous.aqn.service.helper.RemoteDataProviderServiceHelper;
import com.devterous.aqn.settings.AppPreferences;
import com.devterous.aqn.settings.IPreferences;
import com.devterous.aqn.view.IReportingAreaListView;

public class ReportingAreaListPresenter implements IReportingAreaListPresenter<IReportingAreaListView<ListView>>{
	
	private IReportingAreaListView<ListView> mReportingAreaListView;
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
		mReportingAreaListView = view;
		mDataProviderServiceHelper = dataProviderServiceHelper;
	}
	
	public void initialize(PresenterInitializeParameter parameterObject) {
		mContext = parameterObject.listView.getView().getContext();
		mReportingAreaListView = parameterObject.listView;
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
			
			mReportingAreaListView.setAdapter(mAdapter);
			
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
		
		mReportingAreaListView.onDestroy();
		mReportingAreaListView = null;
	}
	
	public void onPollutantGuideButtonClick() {
		Intent intent = new Intent(mListActivity, PollutantGuideActivity.class);
		mListActivity.startActivity(intent);
	}

	public void onSearchAreaClick() {
		
	}

	public void onAddReportingAreaClick(String zipCode) {
		
			AlertDialog.Builder alert = new AlertDialog.Builder(mContext);
	
			alert.setTitle(mContext.getString(R.string.addReportingArea));
			alert.setMessage(mContext.getString(R.string.enterZipCode));
	
			// Set an EditText view to get user input 
			final EditText input = new EditText(mContext);
			input.setText(zipCode);
			input.setInputType(2);
			input.requestFocus();
			alert.setView(input);
			final ReportingAreaListPresenter presenter = this;
			alert.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int whichButton) {
				mZipCode = input.getText().toString().trim();
				  
				if(mZipCode.length() != 5){
					Toast.makeText(mContext, mContext.getString(R.string.zipCodeDigitLimit), Toast.LENGTH_SHORT).show();
					return;
				}  

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
	
			alert.setNegativeButton(mContext.getString(R.string.decline), new DialogInterface.OnClickListener() {
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
