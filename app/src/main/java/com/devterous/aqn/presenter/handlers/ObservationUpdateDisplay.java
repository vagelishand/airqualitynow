package com.devterous.aqn.presenter.handlers;

import java.text.ParseException;

import android.widget.Toast;

import com.devterous.aqn.R;
import com.devterous.aqn.db.callback.IDataRequestCallback;
import com.devterous.aqn.model.Observation;
import com.devterous.aqn.presenter.ObservationPresenter;
import com.devterous.aqn.presenter.util.IGuiRunnable;
import com.devterous.aqn.presenter.util.ObservedArrayAdapter;

public class ObservationUpdateDisplay implements IGuiRunnable<IDataRequestCallback<Observation>>{
	private IDataRequestCallback<Observation> callback;
	private ObservationPresenter _presenter;
	
	public ObservationUpdateDisplay(ObservationPresenter presenter)
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
				Toast.makeText(_presenter.mContext, "Unable to update display", Toast.LENGTH_SHORT).show();
			}
			
		}
	}

	@SuppressWarnings("unchecked")
	public void setCallback(IDataRequestCallback<?> callback) {
		this.callback = (IDataRequestCallback<Observation>)callback;
	}
}
