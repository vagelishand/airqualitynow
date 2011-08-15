package com.quadrictech.airqualitynow.command;

import android.content.Context;
import android.os.Handler;
import android.os.Message;

import com.quadrictech.airqualitynow.base.callback.IRequestCallback;
import com.quadrictech.airqualitynow.event.GotAllForecastsEvent;
import com.quadrictech.airqualitynow.service.IDataProviderService;
import com.quadrictech.airqualitynow.model.Forecast;

public class CommandGetAllForecasts extends DaoCommand<IRequestCallback<Forecast>> {
	
	public CommandGetAllForecasts(Context context, IDataProviderService dataProviderService, Handler handler){
		mContext = context;
		mDataProviderService = dataProviderService;
		mHandler = handler;
	}
	
	public void execute() {
		 IRequestCallback<Forecast> callback = mDataProviderService.onGetAllForecasts();
		 GotAllForecastsEvent event = new GotAllForecastsEvent(null);
		 event.mRequestCallback = callback;
		 Message m = Message.obtain();
		 m.obj = event;
		 mHandler.sendMessage(m);
	}

}
