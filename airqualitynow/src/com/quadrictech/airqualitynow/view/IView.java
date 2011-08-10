package com.quadrictech.airqualitynow.view;

import android.view.View;

public interface IView<T> {
	public void initialize();
	public T getView();
	public void onDestroy();
	public void onClick(View v);

}
