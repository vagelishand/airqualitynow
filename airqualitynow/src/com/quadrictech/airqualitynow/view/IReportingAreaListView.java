package com.quadrictech.airqualitynow.view;

import com.quadrictech.airqualitynow.presenter.util.ForecastArrayAdapter;

import android.view.View;
import android.widget.AdapterView;

public interface IReportingAreaListView<T> extends IView<T> {
	public void setAdapter(ForecastArrayAdapter adapter);
	public void onItemClick(AdapterView<?> parent, View view, int position, long id);
	public String getEditTextString();
}
