package com.devterous.aqn.presenter.handlers;

import android.widget.Toast;

import com.devterous.aqn.db.callback.IDataRequestCallback;
import com.devterous.aqn.model.ReportingArea;
import com.devterous.aqn.presenter.ReportingAreaListPresenter;
import com.devterous.aqn.presenter.util.IGuiRunnable;
import com.devterous.aqn.service.helper.RemoteDataProviderServiceHelper;

public class ReportingAreaGetByZipCode implements IGuiRunnable<IDataRequestCallback<ReportingArea>>{
	IDataRequestCallback<ReportingArea> callback;
	private ReportingAreaListPresenter _presenter;
	
	public ReportingAreaGetByZipCode(ReportingAreaListPresenter presenter)
	{
		_presenter = presenter;
	}
	
	public void run() {
		if(callback.getErrorStatus()){
			Toast.makeText(_presenter.mContext, callback.getErrorMessage(), Toast.LENGTH_SHORT).show();
		}
		//if not found locally search remotely
		else if(!callback.getErrorStatus() && callback.getList().size() == 0){
			RemoteDataProviderServiceHelper.getInstance().getReportingAreaByZipCode(_presenter.mZipCode, new ReportingAreaRemoteDownload(_presenter));
		}
		else{
			_presenter.handleGetReportingAreaByZipCode(callback);
		}
	}

	@SuppressWarnings("unchecked")
	public void setCallback(IDataRequestCallback<?> callback) {
		this.callback = (IDataRequestCallback<ReportingArea>) callback;
	}
}
