package com.devterous.aqn.base.callback;

/**
 * Interface for updating local db or reporting an error received from remote DB
 * @author Art Gonzalez
 *
 */
public interface IRequestCallback<T> {
	public void onError(Throwable exception);
	public boolean getErrorStatus();
	public String getErrorMessage();
}
