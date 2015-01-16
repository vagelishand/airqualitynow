package com.devterous.aqn.presenter.handlers;

import com.devterous.aqn.db.callback.IDataRequestCallback;
import com.devterous.aqn.model.ReportingArea;
import com.devterous.aqn.presenter.ReportingAreaListPresenter;
import com.devterous.aqn.presenter.util.IGuiRunnable;

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
