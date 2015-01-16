package com.devterous.aqn.view;

import java.text.ParseException;
import android.widget.ListView;
import com.facebook.android.Facebook;
import com.devterous.aqn.model.Observation;
import com.devterous.aqn.presenter.IObservationPresenter;
import com.devterous.aqn.presenter.util.ObservedArrayAdapter;

public interface IObservationView<T> extends IView<T> {
	public void initialize(IObservationPresenter<ListView> presenter, String reportingAreaName, Facebook facebook);
	public void setObservationTableValues(Observation observation) throws ParseException;
	public void setAdapter(ObservedArrayAdapter observedArrayAdapter);
}
