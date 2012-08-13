package com.quadrictech.airqualitynow.test.db.callback;

import java.util.ArrayList;
import java.util.List;

import junit.framework.TestCase;

import org.junit.After;
import org.junit.Before;

import android.test.suitebuilder.annotation.MediumTest;

import com.quadrictech.airqualitynow.db.callback.IDataRequestCallback;
import com.quadrictech.airqualitynow.db.callback.ObservationRequestCallback;
import com.quadrictech.airqualitynow.model.Observation;

public class ObservedRequestCallbackTest extends TestCase{
	private IDataRequestCallback<Observation> callback;
	
	@Before
	public void setUp()
	{
		callback = new ObservationRequestCallback();
	}
	
	@MediumTest
	public void testOnResponseReceived(){
		List<Observation> observed = new ArrayList<Observation>(){
			private static final long serialVersionUID = 1L;
			{
				add(new  Observation());
				add(new  Observation());
			}
		};
		
		callback.onResponseReceived(observed);
		
		org.junit.Assert.assertNotNull(callback.getList());
		org.junit.Assert.assertEquals(2, callback.getList().size());
	}
	
	@MediumTest
	public void testOnError(){
		Throwable throwable = new Throwable("Test Error");
		callback.onError(throwable);
		
		org.junit.Assert.assertEquals(true, callback.getErrorStatus());
		org.junit.Assert.assertEquals("Test Error", callback.getErrorMessage());
	}
	
	@After
	public void tearDown(){
		callback = null;
	}

}
