package com.quadrictech.airqualitynow.inet.callback;

import com.google.api.client.http.HttpResponse;
import com.quadrictech.airqualitynow.base.callback.IRequestCallback;

public interface IRestRequestCallback extends IRequestCallback<HttpResponse>{
	public void onResponseReceived(HttpResponse response);
	public HttpResponse getResponse();
}
