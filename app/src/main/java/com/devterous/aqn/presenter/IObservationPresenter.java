package com.devterous.aqn.presenter;

import java.text.ParseException;

import com.devterous.aqn.model.Observation;

public interface IObservationPresenter<T> extends IPresenter<T> {
	public void initializeTable();
	public void onSetObservationTableValues(Observation observation) throws ParseException;
}
