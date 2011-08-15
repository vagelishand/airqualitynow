package com.quadrictech.airqualitynow.presenter;

import java.sql.SQLException;
import java.util.List;

import roboguice.event.EventManager;
import roboguice.event.Observes;


import android.app.AlertDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import com.j256.ormlite.support.ConnectionSource;
import com.quadrictech.airqualitynow.R;
import com.quadrictech.airqualitynow.db.ForecastRepository;
import com.quadrictech.airqualitynow.db.IForecastRepository;
import com.quadrictech.airqualitynow.db.callback.ForecastRequestCallback;
import com.quadrictech.airqualitynow.model.Forecast;
import com.quadrictech.airqualitynow.presenter.util.ForecastArrayAdapter;
import com.quadrictech.airqualitynow.service.helper.DataProviderServiceHelper;
import com.quadrictech.airqualitynow.view.IForecastListView;

public class ForecastListPresenter implements IForecastListPresenter<IForecastListView<ListView>>{
	
	private IForecastRepository mRepository;
	private IForecastListView<ListView> mForecastListView;
	private Context mContext;
	private ForecastArrayAdapter mAdapter;
	private List<Forecast> mForecasts;
	private ConnectionSource mConnectionSource;
	public ListViewAsyncTask asyncTask;
	private EventManager mEventManager;
	private DataProviderServiceHelper mDataProviderServiceHelper;
	
	/***
	 * REquired for roboguice parameter injection
	 */
	public ForecastListPresenter(){
		
	}
	
	public ForecastListPresenter(IForecastListView<ListView> view, IForecastRepository repository){
		mForecastListView = view;
		mRepository = repository;
	}
	
	public void initialize(PresenterInitializeParameter parameterObject) {
		mContext = parameterObject.view.getView().getContext();
		mConnectionSource = parameterObject.connectionSource;
		mForecastListView = parameterObject.view;
		mEventManager = parameterObject.eventManager;
		mDataProviderServiceHelper = (DataProviderServiceHelper) parameterObject.dataProviderServiceHelper;
		
		try {
			mRepository = new ForecastRepository(mConnectionSource);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		initializeList();
	}

	public void initializeList(){
		//asyncTask = new ListViewAsyncTask();
		//asyncTask.execute();
		
		mDataProviderServiceHelper.getAllForecasts();
	}
	
	protected void handleGetAllForecasts(@Observes ForecastRequestCallback callback){
		Toast.makeText(mContext, "You won't regret it!", Toast.LENGTH_LONG).show();
		//mForecasts = callback.getForecasts();
		//mAdapter = new ForecastArrayAdapter(mContext, R.layout.forecastlistrow, mForecasts);
		//mForecastListView.getView().setAdapter(mAdapter);
	}

	public void onDestroy() {
		mAdapter.clear();
		mAdapter = null;
		mForecasts.clear();
		mForecasts = null;
		mForecastListView.onDestroy();
		mForecastListView = null;
	}
	
	public class ListViewAsyncTask extends AsyncTask<Void, Void, Void>{
		
		
		final Handler adapterHandler = new Handler(){
			@Override
			public void handleMessage(Message msg){
				mAdapter = new ForecastArrayAdapter(mContext, R.layout.forecastlistrow, mForecasts);
				mForecastListView.getView().setAdapter(mAdapter);
			}
		};
		
		@Override
		protected Void doInBackground(Void... arg0) {
			try {
				mForecasts = mRepository.queryForAll();
				adapterHandler.sendEmptyMessage(0);				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return null;
		}
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
