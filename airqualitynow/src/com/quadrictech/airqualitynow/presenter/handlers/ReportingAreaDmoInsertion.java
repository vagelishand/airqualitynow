package com.quadrictech.airqualitynow.presenter.handlers;

import android.widget.Toast;

import com.quadrictech.airqualitynow.db.callback.IDataRequestCallback;
import com.quadrictech.airqualitynow.presenter.ReportingAreaListPresenter;
import com.quadrictech.airqualitynow.presenter.util.IGuiRunnable;

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
