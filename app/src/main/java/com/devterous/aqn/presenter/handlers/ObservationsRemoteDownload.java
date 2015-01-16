package com.devterous.aqn.presenter.handlers;

import android.widget.Toast;

import com.devterous.aqn.db.callback.IDataRequestCallback;
import com.devterous.aqn.model.Observation;
import com.devterous.aqn.model.ReportingArea;
import com.devterous.aqn.presenter.ObservationPresenter;
import com.devterous.aqn.presenter.util.IGuiRunnable;
import com.devterous.aqn.service.helper.DataProviderServiceHelper;

public class ObservationsRemoteDownload implements IGuiRunnable<IDataRequestCallback<Observation>>{
	IDataRequestCallback<Observation> callback;
	ObservationPresenter _presenter;
	
	public ObservationsRemoteDownload(ObservationPresenter presenter)
	{
		_presenter = presenter;
	}
	
	public void run() {
		if(callback.getErrorStatus()){
			Toast.makeText(_presenter.mContext, callback.getErrorMessage(), Toast.LENGTH_SHORT).show();
		}
		else
		{
			if(callback.getList().size() > 0){
				ReportingArea area = new ReportingArea();
				area.Id = _presenter.mCurrentReportingAreaId;
				
				DataProviderServiceHelper.getInstance().insertObservations(area, callback.getList(), new ObservationUpdateDisplay(_presenter));
			}
		}
		
	}

	@SuppressWarnings("unchecked")
	public void setCallback(IDataRequestCallback<?> callback) {
		this.callback = (IDataRequestCallback<Observation>) callback;
	}
	
}
