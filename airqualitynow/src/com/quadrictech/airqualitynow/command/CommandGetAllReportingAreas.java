package com.quadrictech.airqualitynow.command;

import android.content.Context;
import android.os.Handler;
import android.os.Message;

import com.quadrictech.airqualitynow.db.callback.ILocalRequestCallback;
import com.quadrictech.airqualitynow.event.GotAllReportingAreasEvent;
import com.quadrictech.airqualitynow.model.ReportingArea;
import com.quadrictech.airqualitynow.service.IDataProviderService;

public class CommandGetAllReportingAreas extends DaoCommand<ReportingArea> {

	public CommandGetAllReportingAreas(Context context, IDataProviderService dataProviderService, Handler handler){
		mContext = context;
		mDataProviderService = dataProviderService;
		mHandler = handler;
	}
	
	public void execute() {
		ILocalRequestCallback<ReportingArea> callback = mDataProviderService.onGetAllReportingAreas();
		GotAllReportingAreasEvent event = new GotAllReportingAreasEvent(null);
		event.mRequestCallback = callback;
		Message msg = Message.obtain();
		msg.obj = event;
		mHandler.sendMessage(msg);
	}

}
