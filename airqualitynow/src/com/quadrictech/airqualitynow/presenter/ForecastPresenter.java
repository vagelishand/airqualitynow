package com.quadrictech.airqualitynow.presenter;

import android.content.Context;
import android.view.View;
import com.quadrictech.airqualitynow.service.helper.IRemoteDataProviderServiceHelper;
import com.quadrictech.airqualitynow.view.IForecastView;

public class ForecastPresenter implements IForecastPresenter<IForecastView<View>> {

	private IForecastView<View> mForecastView;
	private Context mContext;
	
	public ForecastPresenter(){
		
	}
	
	public ForecastPresenter(IForecastView<View> view, IRemoteDataProviderServiceHelper dataProviderServiceHelper){
		mForecastView = view;
	}
	
	public void initialize(PresenterInitializeParameter parameterObject) {
		mContext = parameterObject.view.getView().getContext();
		mForecastView = parameterObject.view;
	}
	
	public void initializeTable(){
		
	} 

	public void onDestroy() {
		mForecastView.onDestroy();		
	}
	
	public void setForecastTableValues(){
		
	}
	
	

}
