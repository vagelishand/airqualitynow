package com.quadrictech.airqualitynow.presenter.handlers;

import com.quadrictech.airqualitynow.db.callback.IDataRequestCallback;
import com.quadrictech.airqualitynow.model.ReportingArea;
import com.quadrictech.airqualitynow.presenter.ReportingAreaListPresenter;
import com.quadrictech.airqualitynow.presenter.util.IGuiRunnable;

public class ReportingAreaGetAll implements IGuiRunnable<IDataRequestCallback<ReportingArea>>{
	IDataRequestCallback<ReportingArea> callback;
	private ReportingAreaListPresenter _presenter;
	
	public ReportingAreaGetAll(ReportingAreaListPresenter presenter)
	{
		_presenter = presenter;
	}
	
	public void run() {
		_presenter.handleGetReportingAreas(callback);			
	}

	@SuppressWarnings("unchecked")
	public void setCallback(IDataRequestCallback<?> callback) {
		this.callback = (IDataRequestCallback<ReportingArea>) callback;
	}
}
