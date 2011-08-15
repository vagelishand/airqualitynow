package com.quadrictech.airqualitynow.base.callback;

import java.util.List;

/**
 * Interface for updating local db or reporting an error received from remote DB
 * @author Art Gonzalez
 *
 */
public interface IRequestCallback<T>{
	public void onError(Throwable exception);
	public void onResponseReceived(T response);
	public boolean getErrorStatus();
	public String getErrorMessage();
	public List<T> getList();
}
