package com.devterous.aqn.base.callback;

import java.util.List;

public interface IRequestCallbackListable<T> {
	public void onResponseReceived(List<T> response);
	public List<T> getList();
}
