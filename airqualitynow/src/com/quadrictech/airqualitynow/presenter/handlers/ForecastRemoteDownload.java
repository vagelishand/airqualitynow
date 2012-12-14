package com.quadrictech.airqualitynow.presenter.handlers;

import android.widget.Toast;

import com.quadrictech.airqualitynow.db.callback.IDataRequestCallback;
import com.quadrictech.airqualitynow.model.Forecast;
import com.quadrictech.airqualitynow.model.ReportingArea;
import com.quadrictech.airqualitynow.presenter.ForecastPresenter;
import com.quadrictech.airqualitynow.presenter.util.IGuiRunnable;
import com.quadrictech.airqualitynow.service.helper.DataProviderServiceHelper;

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
