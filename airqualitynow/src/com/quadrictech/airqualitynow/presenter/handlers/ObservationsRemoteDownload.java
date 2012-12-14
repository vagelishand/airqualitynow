package com.quadrictech.airqualitynow.presenter.handlers;

import android.widget.Toast;

import com.quadrictech.airqualitynow.db.callback.IDataRequestCallback;
import com.quadrictech.airqualitynow.model.Observation;
import com.quadrictech.airqualitynow.model.ReportingArea;
import com.quadrictech.airqualitynow.presenter.ObservationPresenter;
import com.quadrictech.airqualitynow.presenter.util.IGuiRunnable;
import com.quadrictech.airqualitynow.service.helper.DataProviderServiceHelper;

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
