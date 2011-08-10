package com.quadrictech.airqualitynow.view;

import android.view.View;
import android.widget.AdapterView;

public interface IForecastListView<T> extends IView<T> {
	public void onItemClick(AdapterView<?> parent, View view, int position, long id);
}
