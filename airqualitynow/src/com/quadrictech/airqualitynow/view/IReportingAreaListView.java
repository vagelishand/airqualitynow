package com.quadrictech.airqualitynow.view;

import com.quadrictech.airqualitynow.presenter.util.ReportingAreaArrayAdapter;

import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;

public interface IReportingAreaListView<T> extends IView<T> {
	public void setAdapter(ReportingAreaArrayAdapter adapter);
	public void onItemClick(AdapterView<?> parent, View view, int position, long id);
	public boolean onMenuItemClick(MenuItem item);
	public boolean onContextItemSelected(MenuItem item);
	public String getEditTextString();
}
