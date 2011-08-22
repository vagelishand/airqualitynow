package com.quadrictech.airqualitynow.presenter;

import java.util.List;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.quadrictech.airqualitynow.R;
import com.quadrictech.airqualitynow.db.callback.IDataRequestCallback;
import com.quadrictech.airqualitynow.inet.callback.IRemoteRequestCallback;
import com.quadrictech.airqualitynow.model.Observed;
import com.quadrictech.airqualitynow.model.ReportingArea;
import com.quadrictech.airqualitynow.presenter.util.ReportingAreaArrayAdapter;
import com.quadrictech.airqualitynow.presenter.util.IGuiRunnable;
import com.quadrictech.airqualitynow.service.helper.DataProviderServiceHelper;
import com.quadrictech.airqualitynow.service.helper.IDataProviderServiceHelper;
import com.quadrictech.airqualitynow.service.helper.IRemoteDataProviderServiceHelper;
import com.quadrictech.airqualitynow.service.helper.RemoteDataProviderServiceHelper;
import com.quadrictech.airqualitynow.view.IReportingAreaListView;

public class ReportingAreaListPresenter implements IReportingAreaListPresenter<IReportingAreaListView<ListView>>{
	
	private IReportingAreaListView<ListView> mForecastListView;
	private Context mContext;
	private ReportingAreaArrayAdapter mAdapter;
	private List<ReportingArea> mReportingAreas;
	private IDataProviderServiceHelper mDataProviderServiceHelper;
	private IRemoteDataProviderServiceHelper mRemoteDataProviderServiceHelper;
	private String mZipCode;
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
			mAdapter = new ReportingAreaArrayAdapter(mContext, R.layout.forecastlistrow, mReportingAreas);
			
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
	
	public void handleInsertReportingAreaCallback(IDataRequestCallback<ReportingArea> callback){
		if(callback.getErrorStatus()){
			Toast.makeText(mContext, callback.getErrorMessage(), Toast.LENGTH_SHORT).show();
		}
		else{
				ReportingArea area = callback.getList().get(0);
				mAdapter.add(area);
		}
	}
	
	class HandleInsertReportingAreaCallback implements IGuiRunnable<IDataRequestCallback<ReportingArea>>{
		IDataRequestCallback<ReportingArea> callback;
		
		public void run() {
			handleInsertReportingAreaCallback(callback);
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
				RemoteDataProviderServiceHelper.getInstance().getReportingAreaByZipCode(mZipCode, new HandleGetObservedByZipCodeRemote ());
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
	
	class HandleGetObservedByZipCodeRemote implements IGuiRunnable<IDataRequestCallback<Observed>>{
		IDataRequestCallback<Observed> callback;
		
		public void run() {
			if(callback.getErrorStatus()){
				Toast.makeText(mContext, callback.getErrorMessage(), Toast.LENGTH_SHORT).show();
			}
			else{
				ReportingArea area = new ReportingArea();
				Observed observed = callback.getList().get(0);
				area.Name = observed.ReportingArea;
				area.ZipCode = mZipCode;
				area.ObservedAQI = observed.AQI;
				area.State = observed.StateCode;
				
				DataProviderServiceHelper.getInstance().insertReportingArea(area, new HandleInsertReportingAreaCallback());
			}
		}

		public void setCallback(IDataRequestCallback<?> callback) {
			// TODO Auto-generated method stub
			
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
					if(mAdapter.getItem(i).ZipCode.compareTo(mZipCode) == 0){
						Toast.makeText(mContext, mZipCode + " already exists.", Toast.LENGTH_SHORT).show();
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
			Toast.makeText(mContext, this.mForecastListView.getEditTextString(), Toast.LENGTH_SHORT).show();
	}
}
