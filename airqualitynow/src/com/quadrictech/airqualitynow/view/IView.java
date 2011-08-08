package com.quadrictech.airqualitynow.view;

public interface IView<T> {
	public void initialize();
	public T getView();
	public void onDestroy();
}
