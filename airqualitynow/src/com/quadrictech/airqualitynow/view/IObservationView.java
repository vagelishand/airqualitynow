package com.quadrictech.airqualitynow.view;

import java.text.ParseException;

import android.widget.ListView;

import com.facebook.android.Facebook;
import com.quadrictech.airqualitynow.model.Observation;
import com.quadrictech.airqualitynow.presenter.IObservationPresenter;
import com.quadrictech.airqualitynow.presenter.util.ObservedArrayAdapter;

public interface IObservationView<T> extends IView<T> {
	public void initialize(IObservationPresenter<ListView> presenter, String reportingAreaName, Facebook facebook);
	public void setObservationTableValues(Observation observation) throws ParseException;
	public void setAdapter(ObservedArrayAdapter observedArrayAdapter);
}
