package com.devterous.aqn.inet.callback;

import com.google.api.client.http.HttpResponse;
import com.devterous.aqn.base.callback.IRequestCallback;

public interface IRestRequestCallback extends IRequestCallback<HttpResponse>{
	public void onResponseReceived(HttpResponse response);
	public HttpResponse getResponse();
}
