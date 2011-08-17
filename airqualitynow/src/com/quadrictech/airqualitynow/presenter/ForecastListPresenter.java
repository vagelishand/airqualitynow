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
import com.quadrictech.airqualitynow.model.Forecast;
import com.quadrictech.airqualitynow.presenter.util.ForecastArrayAdapter;
import com.quadrictech.airqualitynow.service.helper.IDataProviderServiceHelper;
import com.quadrictech.airqualitynow.view.IForecastListView;

public class ForecastListPresenter implements IForecastListPresenter<IForecastListView<ListView>>{
	
	private IForecastListView<ListView> mForecastListView;
	private Context mContext;
	private ForecastArrayAdapter mAdapter;
	private List<Forecast> mForecasts;
	private IDataProviderServiceHelper mDataProviderServiceHelper;
	
	/***
	 * REquired for roboguice parameter injection
	 */
	public ForecastListPresenter(){
		
	}
	
	public ForecastListPresenter(IForecastListView<ListView> view, IDataProviderServiceHelper dataProviderServiceHelper){
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
			mDataProviderServiceHelper.getAllForecasts(new HandleGetForecasts());
		}
	}

	protected void handleForecasts(ILocalRequestCallback<Forecast> c){
		ILocalRequestCallback<Forecast> callback =  (ILocalRequestCallback<Forecast>) c;
		
		if(callback.getErrorStatus()){
			Toast.makeText(mContext, callback.getErrorMessage(), Toast.LENGTH_SHORT).show();			
		}
		else{
			mForecasts =  (List<Forecast>) callback.getList();
			mAdapter = new ForecastArrayAdapter(mContext, R.layout.forecastlistrow, mForecasts);
			
			mForecastListView.getView().setAdapter(mAdapter);
		}
	}	

	public interface GuiRunnable<T> extends Runnable{
		public void setCallback(ILocalRequestCallback<?> callback);
	}
	
	class HandleGetForecasts implements GuiRunnable<ILocalRequestCallback<Forecast>>{
		ILocalRequestCallback<Forecast> callback;
		
		public void run() {
			handleForecasts(callback);			
		}

		@SuppressWarnings("unchecked")
		public void setCallback(ILocalRequestCallback<?> callback) {
			this.callback = (ILocalRequestCallback<Forecast>) callback;
		}
	}
	
	public void onDestroy() {
		
		if(mAdapter != null){
			mAdapter.clear();
			mAdapter = null;
		}
		
		if(mForecasts != null){
			mForecasts.clear();
			mForecasts = null;
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
}
