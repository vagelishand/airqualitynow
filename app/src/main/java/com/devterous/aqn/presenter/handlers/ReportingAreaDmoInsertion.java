package com.devterous.aqn.presenter.handlers;

import android.widget.Toast;

import com.devterous.aqn.db.callback.IDataRequestCallback;
import com.devterous.aqn.presenter.ReportingAreaListPresenter;
import com.devterous.aqn.presenter.util.IGuiRunnable;

public class ReportingAreaDmoInsertion implements IGuiRunnable<IDataRequestCallback<?>>{
	private IDataRequestCallback<?> callback;
	private ReportingAreaListPresenter _presenter;
	
	public ReportingAreaDmoInsertion(ReportingAreaListPresenter presenter)
	{
		_presenter = presenter;
	}
	
	public void run() {
		if(callback.getErrorStatus()){
			Toast.makeText(_presenter.mContext, callback.getErrorMessage(), Toast.LENGTH_SHORT).show();
		}
	}

	public void setCallback(IDataRequestCallback<?> callback) {
		this.callback = callback;
	}
}
