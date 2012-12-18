package com.quadrictech.airqualitynow.presenter;

import java.text.ParseException;

import android.content.Context;
import android.view.View;
import android.widget.Toast;

import com.quadrictech.airqualitynow.db.callback.IDataRequestCallback;
import com.quadrictech.airqualitynow.model.Forecast;
import com.quadrictech.airqualitynow.presenter.handlers.ForecastRemoteDownload;
import com.quadrictech.airqualitynow.presenter.util.IGuiRunnable;
import com.quadrictech.airqualitynow.service.helper.DataProviderServiceHelper;
import com.quadrictech.airqualitynow.service.helper.IRemoteDataProviderServiceHelper;
import com.quadrictech.airqualitynow.service.helper.RemoteDataProviderServiceHelper;
import com.quadrictech.airqualitynow.utils.DateUtil;
import com.quadrictech.airqualitynow.view.IForecastView;

public class ForecastPresenter implements IForecastPresenter<IForecastView<View>> {

	public IForecastView<View> mForecastView;
	public Context mContext;
	public int mCurrentReportingAreaId;
	public String mZipCode;
	
	public ForecastPresenter(){
		
	}
	
	public ForecastPresenter(IForecastView<View> view, IRemoteDataProviderServiceHelper dataProviderServiceHelper){
		mForecastView = view;
	}
	
	public void initialize(PresenterInitializeParameter parameterObject) {
		mContext = parameterObject.forecastView.getView().getContext();
		mForecastView = parameterObject.forecastView;
		mCurrentReportingAreaId = parameterObject.reportingAreaId;
		mZipCode = parameterObject.zipCode;
		initializeTable();
	}
	
	public void initializeTable(){
		
		try {
			DataProviderServiceHelper.getInstance().getForecastsByReportingAreaId(mCurrentReportingAreaId, DateUtil.getForecastIssueDate(), new HandleGetForecastsById(this));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	} 

	class HandleGetForecastsById implements IGuiRunnable<IDataRequestCallback<Forecast>>{
		IDataRequestCallback<Forecast> callback;
		ForecastPresenter _presenter;
		
		public HandleGetForecastsById(ForecastPresenter presenter){
			_presenter = presenter;
		}
		
		public void run() {
			if(callback.getErrorStatus()){
				Toast.makeText(mContext, callback.getErrorMessage(), Toast.LENGTH_SHORT).show();
			}
			else if(callback.getList().size() == 0){
				RemoteDataProviderServiceHelper.getInstance().getForecastsByZipCode(_presenter.mZipCode, new ForecastRemoteDownload(_presenter));
			}
			else{
				mForecastView.setForecastTableValues(callback.getList());
			}
		}

		@SuppressWarnings("unchecked")
		public void setCallback(IDataRequestCallback<?> callback) {
			this.callback = (IDataRequestCallback<Forecast>) callback;
		}
		
	}
	
	public void onDestroy() {
		mForecastView.onDestroy();		
	}

	public void onSetTodayForecastTableValues(Forecast forecast) {
		mForecastView.setTodayForecastTableValues(forecast);		
	}

	public void onSetTomorrowForecastTableValues(Forecast forecast) {
		mForecastView.setTomorrowForecastTableValues(forecast);		
	}

}
