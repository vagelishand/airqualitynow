package com.devterous.aqn.presenter.handlers;

import android.widget.Toast;

import com.devterous.aqn.db.callback.IDataRequestCallback;
import com.devterous.aqn.model.Forecast;
import com.devterous.aqn.model.ReportingArea;
import com.devterous.aqn.presenter.ForecastPresenter;
import com.devterous.aqn.presenter.util.IGuiRunnable;
import com.devterous.aqn.service.helper.DataProviderServiceHelper;

public class ForecastRemoteDownload implements IGuiRunnable<IDataRequestCallback<Forecast>> {
	ForecastPresenter _presenter;
	IDataRequestCallback<Forecast> mCallback;
	
	public ForecastRemoteDownload(ForecastPresenter presenter) {
		_presenter = presenter;
	}

	public void run() {
		if(mCallback.getErrorStatus()){
			Toast.makeText(_presenter.mContext, mCallback.getErrorMessage(), Toast.LENGTH_SHORT).show();
		}
		else
		{
			if(mCallback.getList().size() > 0){
				ReportingArea area = new ReportingArea();
				area.Id = _presenter.mCurrentReportingAreaId;
				
				DataProviderServiceHelper.getInstance().insertForecasts(area, 
																		mCallback.getList(), 
																		new ForecastUpdateDisplay(_presenter));
			}
		}
		
	}

	@SuppressWarnings("unchecked")
	public void setCallback(IDataRequestCallback<?> callback) {
		mCallback = (IDataRequestCallback<Forecast>) callback;
	}

}
