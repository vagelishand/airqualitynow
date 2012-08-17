package com.quadrictech.airqualitynow.presenter.handlers;

import android.widget.Toast;

import com.quadrictech.airqualitynow.db.callback.IDataRequestCallback;
import com.quadrictech.airqualitynow.model.ReportingArea;
import com.quadrictech.airqualitynow.presenter.ReportingAreaListPresenter;
import com.quadrictech.airqualitynow.presenter.util.IGuiRunnable;
import com.quadrictech.airqualitynow.service.helper.RemoteDataProviderServiceHelper;

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
