package com.devterous.aqn.view;

import com.devterous.aqn.presenter.util.ReportingAreaArrayAdapter;

import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.view.ContextMenu.ContextMenuInfo;
import android.widget.AdapterView;

public interface IReportingAreaListView<T> extends IView<T> {
	public void setAdapter(ReportingAreaArrayAdapter adapter);
	public void onItemClick(AdapterView<?> parent, View view, int position, long id);
	public boolean onMenuItemClick(MenuItem item);
	public boolean onContextItemSelected(MenuItem item);
	public void handleOnCreateContextMenu(ContextMenu menu, View view, ContextMenuInfo menuInfo);
	public String getEditTextString();
}
