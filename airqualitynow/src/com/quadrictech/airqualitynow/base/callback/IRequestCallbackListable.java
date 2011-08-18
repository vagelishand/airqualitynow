package com.quadrictech.airqualitynow.base.callback;

import java.util.List;

public interface IRequestCallbackListable<T> {
	public void onResponseReceived(List<T> response);
	public List<T> getList();
}
