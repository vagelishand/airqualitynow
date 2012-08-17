package com.quadrictech.airqualitynow.presenter.handlers;

import java.text.ParseException;

import android.widget.Toast;

import com.quadrictech.airqualitynow.R;
import com.quadrictech.airqualitynow.db.callback.IDataRequestCallback;
import com.quadrictech.airqualitynow.model.Observation;
import com.quadrictech.airqualitynow.presenter.ObservationPresenter;
import com.quadrictech.airqualitynow.presenter.util.IGuiRunnable;
import com.quadrictech.airqualitynow.presenter.util.ObservedArrayAdapter;
import com.quadrictech.airqualitynow.service.helper.RemoteDataProviderServiceHelper;

public class ObservationGetById implements IGuiRunnable<IDataRequestCallback<Observation>>{
	IDataRequestCallback<Observation> callback;
	private ObservationPresenter _presenter;
	
	public ObservationGetById(ObservationPresenter presenter)
	{
		_presenter = presenter;
	}
	
	public void run() {
		if(callback.getErrorStatus()){
			Toast.makeText(_presenter.mContext, callback.getErrorMessage(), Toast.LENGTH_SHORT).show();
		}//no data locally do remote download
		else if(callback.getList().size() == 0)
		{
			RemoteDataProviderServiceHelper.getInstance().getObservationsByZipCode(_presenter.mZipCode, new ObservationsRemoteDownload(_presenter));
		}
		else{
			try{
				if(callback.getList().size() > 0){
					_presenter.onSetObservationTableValues(callback.getList().get(0));
				}
				_presenter.mArrayAdapter = new ObservedArrayAdapter(_presenter.mContext, R.id.observationList, callback.getList());
				
				_presenter.mObservedView.setAdapter(_presenter.mArrayAdapter);
			}
			catch(ParseException e){
				Toast.makeText(_presenter.mContext, "Error parsing Date", Toast.LENGTH_SHORT).show();
			}
		}			
	}

	@SuppressWarnings("unchecked")
	public void setCallback(IDataRequestCallback<?> callback) {
		this.callback = (IDataRequestCallback<Observation>) callback;
	}
}

