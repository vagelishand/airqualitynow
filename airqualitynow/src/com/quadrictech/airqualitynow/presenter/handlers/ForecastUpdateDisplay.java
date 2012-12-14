package com.quadrictech.airqualitynow.presenter.handlers;

import android.widget.Toast;

import com.quadrictech.airqualitynow.db.callback.IDataRequestCallback;
import com.quadrictech.airqualitynow.model.Forecast;
import com.quadrictech.airqualitynow.presenter.ForecastPresenter;
import com.quadrictech.airqualitynow.presenter.util.IGuiRunnable;

public class ForecastUpdateDisplay implements IGuiRunnable<IDataRequestCallback<Forecast>> {
	ForecastPresenter mPresenter;
	IDataRequestCallback<Forecast> mCallback;
	
	public ForecastUpdateDisplay(ForecastPresenter presenter) {
		mPresenter = presenter;
	}

	public void run() {
		if(mCallback.getErrorStatus()){
			Toast.makeText(mPresenter.mContext, mCallback.getErrorMessage(), Toast.LENGTH_SHORT).show();
		}
		else{
			mPresenter.mForecastView.setForecastTableValues(mCallback.getList());
		}
	}

	@SuppressWarnings("unchecked")
	public void setCallback(IDataRequestCallback<?> callback) {
		mCallback = (IDataRequestCallback<Forecast>) callback;
	}

}
