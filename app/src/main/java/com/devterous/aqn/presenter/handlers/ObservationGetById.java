package com.devterous.aqn.presenter.handlers;

import java.text.ParseException;

import android.widget.Toast;

import com.devterous.aqn.R;
import com.devterous.aqn.db.callback.IDataRequestCallback;
import com.devterous.aqn.model.Observation;
import com.devterous.aqn.presenter.ObservationPresenter;
import com.devterous.aqn.presenter.util.IGuiRunnable;
import com.devterous.aqn.presenter.util.ObservedArrayAdapter;
import com.devterous.aqn.service.helper.RemoteDataProviderServiceHelper;

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

