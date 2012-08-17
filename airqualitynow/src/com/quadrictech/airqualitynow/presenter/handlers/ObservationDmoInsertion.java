package com.quadrictech.airqualitynow.presenter.handlers;

import java.text.ParseException;

import android.widget.Toast;

import com.quadrictech.airqualitynow.R;
import com.quadrictech.airqualitynow.db.callback.IDataRequestCallback;
import com.quadrictech.airqualitynow.model.Observation;
import com.quadrictech.airqualitynow.presenter.ObservationPresenter;
import com.quadrictech.airqualitynow.presenter.util.IGuiRunnable;
import com.quadrictech.airqualitynow.presenter.util.ObservedArrayAdapter;

public class ObservationDmoInsertion implements IGuiRunnable<IDataRequestCallback<Observation>>{
	private IDataRequestCallback<Observation> callback;
	private ObservationPresenter _presenter;
	
	public ObservationDmoInsertion(ObservationPresenter presenter)
	{
		_presenter = presenter;
	}
	
	public void run() {
		if(callback.getErrorStatus()){
			Toast.makeText(_presenter.mContext, callback.getErrorMessage(), Toast.LENGTH_SHORT).show();
		}
		else
		{
			_presenter.mArrayAdapter = new ObservedArrayAdapter(_presenter.mContext, R.id.observationList, callback.getList());
			
		    _presenter.mObservedView.setAdapter(_presenter.mArrayAdapter);

			try {
				_presenter.onSetObservationTableValues(callback.getList().get(0));
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	}

	@SuppressWarnings("unchecked")
	public void setCallback(IDataRequestCallback<?> callback) {
		this.callback = (IDataRequestCallback<Observation>)callback;
	}
}
