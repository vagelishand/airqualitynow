package com.quadrictech.airqualitynow.presenter;

import java.text.ParseException;

import com.quadrictech.airqualitynow.model.Observation;

public interface IObservationPresenter<T> extends IPresenter<T> {
	public void initializeTable();
	public void onSetObservationTableValues(Observation observation) throws ParseException;
}
