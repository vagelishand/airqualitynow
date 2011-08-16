package com.quadrictech.airqualitynow.inet.callback;

import com.google.api.client.http.HttpResponse;
import com.quadrictech.airqualitynow.base.callback.IRequestCallback;
import com.quadrictech.airqualitynow.base.callback.IRequestCallbackError;

public interface IRestRequestCallback extends IRequestCallback<HttpResponse>, IRequestCallbackError {

}
