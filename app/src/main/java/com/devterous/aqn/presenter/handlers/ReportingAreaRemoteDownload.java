package com.devterous.aqn.presenter.handlers;

import java.util.List;

import android.widget.Toast;

import com.devterous.aqn.db.callback.IDataRequestCallback;
import com.devterous.aqn.inet.callback.RemoteCallbackData;
import com.devterous.aqn.model.Forecast;
import com.devterous.aqn.model.ReportingArea;
import com.devterous.aqn.model.util.ForecastUtil;
import com.devterous.aqn.model.util.IForecastUtil;
import com.devterous.aqn.presenter.ReportingAreaListPresenter;
import com.devterous.aqn.presenter.util.IGuiRunnable;
import com.devterous.aqn.service.helper.DataProviderServiceHelper;
import com.devterous.aqn.settings.AppPreferences;
import com.devterous.aqn.settings.IPreferences;

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
			
			if(_presenter.mAdapter.isEmpty()){
				IPreferences pref = new AppPreferences(_presenter.mContext);
				pref.setDefaultReportingAreaId(area.Id);
				pref.setDefaultReportingArea(area.Name);
			}
			
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

