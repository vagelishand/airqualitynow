package com.quadrictech.airqualitynow.command;

import android.content.Context;
import android.os.Handler;
import android.os.Message;

import com.quadrictech.airqualitynow.base.callback.IRequestCallback;
import com.quadrictech.airqualitynow.event.GotForecastByIdEvent;
import com.quadrictech.airqualitynow.model.Forecast;
import com.quadrictech.airqualitynow.service.IDataProviderService;

public class CommandGetForecastById extends DaoCommand<Forecast> {
	private int mForecastId;
	
	public CommandGetForecastById(int forecastId, Context context, IDataProviderService dataProviderService, Handler handler){
		mForecastId = forecastId;
		mContext = context;
		mDataProviderService = dataProviderService;
		mHandler = handler;
	}
	
	public void execute() {
		IRequestCallback<Forecast> callback = mDataProviderService.onGetForecastById(mForecastId);
		GotForecastByIdEvent event = new GotForecastByIdEvent(null);
		event.mRequestCallback = callback;
		
		Message m = Message.obtain();
		m.obj = event;
		mHandler.sendMessage(m);
	}

}
