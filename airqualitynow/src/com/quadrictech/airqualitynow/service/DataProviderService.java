package com.quadrictech.airqualitynow.service;

import java.sql.SQLException;

import android.content.Intent;
import android.os.IBinder;

import com.j256.ormlite.android.apptools.OrmLiteBaseService;
import com.quadrictech.airqualitynow.db.DatabaseHelper;
import com.quadrictech.airqualitynow.model.State;

public class DataProviderService extends OrmLiteBaseService<DatabaseHelper> {
	@Override
	public IBinder onBind(Intent arg0) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
    public int onStartCommand(Intent intent, int flags, int startId) {
		State state = new State();
		state.Name = "TX";
		
		try{
			getHelper().getStateDAO().create(state);
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		
		return START_NOT_STICKY;
	
	}
}
