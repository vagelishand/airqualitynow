package com.quadrictech.airqualitynow.view;

import com.quadrictech.airqualitynow.presenter.util.ForecastArrayAdapter;

import android.view.View;
import android.widget.AdapterView;

public interface IForecastListView<T> extends IView<T> {
	public void setAdapter(ForecastArrayAdapter adapter);
	public void onItemClick(AdapterView<?> parent, View view, int position, long id);
}
