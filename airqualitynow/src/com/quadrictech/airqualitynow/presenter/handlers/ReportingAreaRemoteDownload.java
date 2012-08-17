package com.quadrictech.airqualitynow.presenter.handlers;

import java.util.List;

import android.widget.Toast;

import com.quadrictech.airqualitynow.db.callback.IDataRequestCallback;
import com.quadrictech.airqualitynow.inet.callback.RemoteCallbackData;
import com.quadrictech.airqualitynow.model.Forecast;
import com.quadrictech.airqualitynow.model.ReportingArea;
import com.quadrictech.airqualitynow.model.util.ForecastUtil;
import com.quadrictech.airqualitynow.model.util.IForecastUtil;
import com.quadrictech.airqualitynow.presenter.ReportingAreaListPresenter;
import com.quadrictech.airqualitynow.presenter.util.IGuiRunnable;
import com.quadrictech.airqualitynow.service.helper.DataProviderServiceHelper;

public class ReportingAreaRemoteDownload implements IGuiRunnable<IDataRequestCallback<RemoteCallbackData>>{
	private IDataRequestCallback<RemoteCallbackData> callback;
	private ReportingAreaListPresenter _presenter;
	
	public ReportingAreaRemoteDownload(ReportingAreaListPresenter presenter)
	{
		_presenter = presenter;
	}
	
	public void run() {
		if(callback.getErrorStatus()){
			Toast.makeText(_presenter.mContext, callback.getErrorMessage(), Toast.LENGTH_SHORT).show();
		}
		else{
			List<RemoteCallbackData> list = callback.getList();
			RemoteCallbackData data = list.get(0);
			ReportingArea area = data.reportingArea;
			_presenter.mAdapter.add(area);
			
			DataProviderServiceHelper.getInstance().insertObservations(area, data.observations, new ReportingAreaDmoInsertion(_presenter));
			IForecastUtil util = new ForecastUtil();
			List<Forecast> forecasts = util.getFirstTwoForecastRecords(data.forecasts, 
					DataProviderServiceHelper.getInstance().getAllPollutants());
			
			DataProviderServiceHelper.getInstance().insertForecasts(area, forecasts, new ReportingAreaDmoInsertion(_presenter));
		}
	}

	@SuppressWarnings("unchecked")
	public void setCallback(IDataRequestCallback<?> callback) {
		this.callback = (IDataRequestCallback<RemoteCallbackData>) callback;			
	}
	
}

