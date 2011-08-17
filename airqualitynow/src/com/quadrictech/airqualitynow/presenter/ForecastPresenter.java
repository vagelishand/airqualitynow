package com.quadrictech.airqualitynow.presenter;

import java.util.List;

import roboguice.event.Observes;

import android.content.Context;
import android.view.View;
import android.widget.Toast;

import com.quadrictech.airqualitynow.event.BindedToServiceEvent;
import com.quadrictech.airqualitynow.event.ObservedDataRetrieved;
import com.quadrictech.airqualitynow.model.Observed;
import com.quadrictech.airqualitynow.service.helper.IRemoteDataProviderServiceHelper;
import com.quadrictech.airqualitynow.view.IForecastView;

public class ForecastPresenter implements IForecastPresenter<IForecastView<View>> {

	private IForecastView<View> mForecastView;
	private IRemoteDataProviderServiceHelper mRemoteDataProviderServiceHelper;
	private Context mContext;
	
	public ForecastPresenter(){
		
	}
	
	public ForecastPresenter(IForecastView<View> view, IRemoteDataProviderServiceHelper dataProviderServiceHelper){
		mForecastView = view;
		mRemoteDataProviderServiceHelper = dataProviderServiceHelper;
	}
	
	public void initialize(PresenterInitializeParameter parameterObject) {
		mContext = parameterObject.view.getView().getContext();
		mForecastView = parameterObject.view;
		mRemoteDataProviderServiceHelper = parameterObject.remoteDataProviderServiceHelper;
	}
	
	public void initializeTable(@Observes BindedToServiceEvent event){
		Toast.makeText(mContext,"retrieving observd", Toast.LENGTH_SHORT).show();
		mRemoteDataProviderServiceHelper.getObservedByZipCode("92401");
	} 

	public void onDestroy() {
		mForecastView.onDestroy();		
	}
	
	public void setForecastTableValues(@Observes ObservedDataRetrieved data){
		List<Observed> observedList = data.mRemoteRequestCallback.getList();
		
		mForecastView.setObservedTableValues(observedList);
	}
	
	

}
