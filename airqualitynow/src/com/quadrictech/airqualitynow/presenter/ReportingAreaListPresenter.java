package com.quadrictech.airqualitynow.presenter;

import java.util.List;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.quadrictech.airqualitynow.ForecastActivity;
import com.quadrictech.airqualitynow.R;
import com.quadrictech.airqualitynow.ReportingAreaListActivity;
import com.quadrictech.airqualitynow.db.callback.IDataRequestCallback;
import com.quadrictech.airqualitynow.inet.callback.RemoteCallbackData;
import com.quadrictech.airqualitynow.model.ReportingArea;
import com.quadrictech.airqualitynow.presenter.util.ReportingAreaArrayAdapter;
import com.quadrictech.airqualitynow.presenter.util.IGuiRunnable;
import com.quadrictech.airqualitynow.service.helper.DataProviderServiceHelper;
import com.quadrictech.airqualitynow.service.helper.IDataProviderServiceHelper;
import com.quadrictech.airqualitynow.service.helper.RemoteDataProviderServiceHelper;
import com.quadrictech.airqualitynow.view.IReportingAreaListView;

public class ReportingAreaListPresenter implements IReportingAreaListPresenter<IReportingAreaListView<ListView>>{
	
	private IReportingAreaListView<ListView> mForecastListView;
	private Context mContext;
	private ReportingAreaArrayAdapter mAdapter;
	private List<ReportingArea> mReportingAreas;
	private IDataProviderServiceHelper mDataProviderServiceHelper;
	private String mZipCode;
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
		initializeList();
	}

	public void initializeList(){
		mDataProviderServiceHelper.getAllReportingAreas(new HandleGetReportingAreas());
	}

	public void handleGetReportingAreas(IDataRequestCallback<ReportingArea> callback){
		
		if(callback.getErrorStatus()){
			Toast.makeText(mContext, callback.getErrorMessage(), Toast.LENGTH_SHORT).show();			
		}
		else{
			mReportingAreas =  (List<ReportingArea>) callback.getList();
			mAdapter = new ReportingAreaArrayAdapter(mContext, R.layout.reportingarealistrow, mReportingAreas);
			
			mForecastListView.setAdapter(mAdapter);
		}
	}	

	class HandleGetReportingAreas implements IGuiRunnable<IDataRequestCallback<ReportingArea>>{
		IDataRequestCallback<ReportingArea> callback;
		
		public void run() {
			handleGetReportingAreas(callback);			
		}

		@SuppressWarnings("unchecked")
		public void setCallback(IDataRequestCallback<?> callback) {
			this.callback = (IDataRequestCallback<ReportingArea>) callback;
		}
	}
	
	public void handleGetReportingAreaByZipCode(IDataRequestCallback<ReportingArea> callback){
		Toast.makeText(mContext, "function", Toast.LENGTH_SHORT).show();
	}
	
	class HandleGetReportingAreaByZipCode implements IGuiRunnable<IDataRequestCallback<ReportingArea>>{
		IDataRequestCallback<ReportingArea> callback;
		
		public void run() {
			if(callback.getErrorStatus()){
				Toast.makeText(mContext, callback.getErrorMessage(), Toast.LENGTH_SHORT).show();
			}
			//if not found locally search remotely
			else if(!callback.getErrorStatus() && callback.getList().size() == 0){
				RemoteDataProviderServiceHelper.getInstance().getReportingAreaByZipCode(mZipCode, new HandleRemote());
			}
			else{
				handleGetReportingAreaByZipCode(callback);
			}
		}

		@SuppressWarnings("unchecked")
		public void setCallback(IDataRequestCallback<?> callback) {
			this.callback = (IDataRequestCallback<ReportingArea>) callback;
		}
	}
	
	class HandleRemote implements IGuiRunnable<IDataRequestCallback<RemoteCallbackData>>{
		private IDataRequestCallback<RemoteCallbackData> callback;
		
		public void run() {
			if(callback.getErrorStatus()){
				Toast.makeText(mContext, callback.getErrorMessage(), Toast.LENGTH_SHORT).show();
			}
			else{
				List<RemoteCallbackData> list = callback.getList();
				RemoteCallbackData data = list.get(0);
				ReportingArea area = data.reportingArea;
				mAdapter.add(area);
				
				DataProviderServiceHelper.getInstance().insertObserved(area, data.observed, new HandleDmoInsertion());
				DataProviderServiceHelper.getInstance().insertForecast(area, data.forecasts, new HandleDmoInsertion());
			}
		}

		@SuppressWarnings("unchecked")
		public void setCallback(IDataRequestCallback<?> callback) {
			this.callback = (IDataRequestCallback<RemoteCallbackData>) callback;			
		}
		
	}
	
	class HandleDmoInsertion implements IGuiRunnable<IDataRequestCallback<?>>{
		private IDataRequestCallback<?> callback;
		
		public void run() {
			if(callback.getErrorStatus()){
				Toast.makeText(mContext, callback.getErrorMessage(), Toast.LENGTH_SHORT).show();
				Log.d("presenter", callback.getErrorMessage());
			}
		}

		public void setCallback(IDataRequestCallback<?> callback) {
			this.callback = callback;
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
		
			AlertDialog.Builder alert = new AlertDialog.Builder(mContext);
	
			alert.setTitle("Add Reporting Area");
			alert.setMessage("Enter Zip Code");
	
			// Set an EditText view to get user input 
			final EditText input = new EditText(mContext);
			input.setInputType(2);
			input.requestFocus();
			alert.setView(input);
	
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
			  mDataProviderServiceHelper.getReportingAreaByZipCode(mZipCode, new HandleGetReportingAreaByZipCode());
			  }
			});
	
			alert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
			  public void onClick(DialogInterface dialog, int whichButton) {
			    // Canceled.
			  }
			});
	
			alert.show();
	}

	public void onViewForecast(int id) {
		Intent intent = new Intent(mListActivity, ForecastActivity.class);
		intent.putExtra("areaId", id);
		mListActivity.startActivity(intent);
	}

	public void onViewObserved(int id) {
		// TODO Auto-generated method stub
		
	}
}
