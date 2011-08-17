/**
 * 
 */
package com.quadrictech.airqualitynow.event;

import com.quadrictech.airqualitynow.inet.callback.IRemoteRequestCallback;
import com.quadrictech.airqualitynow.model.Observed;
import com.quadrictech.airqualitynow.service.helper.RemoteDataProviderServiceHelper;

/**
 * @author Art
 *
 */
public class ObservedDataRetrieved implements IEvent<RemoteDataProviderServiceHelper> {
	public IRemoteRequestCallback<Observed> mRemoteRequestCallback;
	
	public RemoteDataProviderServiceHelper getSender() {
		// TODO Auto-generated method stub
		return null;
	}
	
}
