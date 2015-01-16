package com.devterous.aqn.presenter.handlers;

import android.widget.Toast;

import com.devterous.aqn.db.callback.IDataRequestCallback;
import com.devterous.aqn.model.Forecast;
import com.devterous.aqn.presenter.ForecastPresenter;
import com.devterous.aqn.presenter.util.IGuiRunnable;

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
