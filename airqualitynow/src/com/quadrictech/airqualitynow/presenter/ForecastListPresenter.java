package com.quadrictech.airqualitynow.presenter;

import java.sql.SQLException;
import java.util.List;


import android.content.Context;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.Message;
import android.widget.ListView;

import com.j256.ormlite.support.ConnectionSource;
import com.quadrictech.airqualitynow.R;
import com.quadrictech.airqualitynow.db.ForecastRepository;
import com.quadrictech.airqualitynow.db.IForecastRepository;
import com.quadrictech.airqualitynow.model.Forecast;
import com.quadrictech.airqualitynow.presenter.util.ForecastArrayAdapter;
import com.quadrictech.airqualitynow.view.IForecastListView;

public class ForecastListPresenter implements IForecastListPresenter<IForecastListView<ListView>>{
	
	private IForecastRepository mRepository;
	private IForecastListView<ListView> mForecastListView;
	private Context mContext;
	private ForecastArrayAdapter mAdapter;
	private List<Forecast> mForecasts;
	private ConnectionSource mConnectionSource;
	
	/***
	 * REquired for roboguice parameter injection
	 */
	public ForecastListPresenter(){
		
	}
	
	public ForecastListPresenter(IForecastListView<ListView> view, IForecastRepository repository){
		mContext = view.getView().getContext();
		mForecastListView = view;
		mRepository = repository;
	}
	
	public void initialize(IForecastListView<ListView> view, ConnectionSource connectionSource) {
		mContext = view.getView().getContext();
		mConnectionSource = connectionSource;
		mForecastListView = view;
		
		try {
			mRepository = new ForecastRepository(mConnectionSource, Forecast.class);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		initializeList();
	}

	public void initializeList(){
		ListViewAsyncTask asyncTask = new ListViewAsyncTask();
		asyncTask.execute();
	}

	public void onDestroy() {
		mAdapter.clear();
		mAdapter = null;
		mForecasts.clear();
		mForecasts = null;
		mForecastListView.onDestroy();
		mForecastListView = null;
	}
	
	class ListViewAsyncTask extends AsyncTask<Void, Void, Void>{
		
		
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
}
