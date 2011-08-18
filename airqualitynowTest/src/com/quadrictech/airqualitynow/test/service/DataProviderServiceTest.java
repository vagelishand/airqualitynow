package com.quadrictech.airqualitynow.test.service;

import com.google.android.testing.mocking.AndroidMock;
import com.google.android.testing.mocking.UsesMocks;
import com.quadrictech.airqualitynow.db.IForecastRepository;
import com.quadrictech.airqualitynow.db.IObservedRepository;
import com.quadrictech.airqualitynow.db.IReportingAreaRepository;
import com.quadrictech.airqualitynow.db.callback.ILocalRequestCallback;
import com.quadrictech.airqualitynow.model.Forecast;
import com.quadrictech.airqualitynow.model.Observed;
import com.quadrictech.airqualitynow.model.ReportingArea;
import com.quadrictech.airqualitynow.service.DataProviderService;

import android.content.Intent;
import android.os.IBinder;
import android.test.ServiceTestCase;
import android.test.suitebuilder.annotation.MediumTest;
import android.test.suitebuilder.annotation.SmallTest;

public class DataProviderServiceTest extends ServiceTestCase<DataProviderService>{
	private Intent startIntent;
	private IForecastRepository  mForecastRepository;
	private IReportingAreaRepository mReportingAreaRepository;
	private IObservedRepository mObservedRepository;
	
	public DataProviderServiceTest() {
		super(DataProviderService.class);
	}
	
	@Override
	public void setUp()throws Exception{
		super.setUp();
		startIntent = new Intent(getContext(), DataProviderService.class);
	}
	
	@SmallTest
	public void testStartable(){
		startService(startIntent);
	}
	
	@SmallTest
	public void testBindable(){
		IBinder service = bindService(startIntent);
		assertNotNull(service);
	}
	
	@UsesMocks(IForecastRepository.class)
	@MediumTest
	public void testGetForecasts(){
		ILocalRequestCallback<Forecast> forecastRequest = null;
		mForecastRepository = AndroidMock.createMock(IForecastRepository.class);
		IBinder binder = bindService(startIntent);
		DataProviderService service = ((DataProviderService.LocalBinder)binder).getService();
		service.initialize(mForecastRepository);
		
		AndroidMock.expect(service.onGetAllForecasts()).andReturn(forecastRequest);
		AndroidMock.replay(mForecastRepository);
		
		service.onGetAllForecasts();
		
		AndroidMock.verify(mForecastRepository);
		
	}

	@UsesMocks(IForecastRepository.class)
	@MediumTest
	public void testGetForecastById(){
		ILocalRequestCallback<Forecast> forecastRequest = null;
		mForecastRepository = AndroidMock.createMock(IForecastRepository.class);
		IBinder binder = bindService(startIntent);
		DataProviderService service = ((DataProviderService.LocalBinder)binder).getService();
		service.initialize(mForecastRepository);
		
		AndroidMock.expect(service.onGetForecastById(AndroidMock.anyInt())).andReturn(forecastRequest);
		AndroidMock.replay(mForecastRepository);
		
		service.onGetForecastById(1);
		
		AndroidMock.verify(mForecastRepository);
		
	}
	
	@UsesMocks(IReportingAreaRepository.class)
	@MediumTest
	public void testGetReportingArea(){
		ILocalRequestCallback<ReportingArea> reportingAreaRequest = null;
		mReportingAreaRepository = AndroidMock.createMock(IReportingAreaRepository.class);
		IBinder binder = bindService(startIntent);
		DataProviderService service = ((DataProviderService.LocalBinder)binder).getService();
		service.initialize(mReportingAreaRepository);
		
		AndroidMock.expect(service.onGetAllReportingAreas()).andReturn(reportingAreaRequest);
		AndroidMock.replay(mReportingAreaRepository);
		
		service.onGetAllReportingAreas();
		
		AndroidMock.verify(mReportingAreaRepository);
	}
	
	@UsesMocks(IObservedRepository.class)
	@MediumTest
	public void testGetObservedByZipCode(){
		ILocalRequestCallback<Observed> observedRequest = null;
		mObservedRepository = AndroidMock.createMock(IObservedRepository.class);
		IBinder binder = bindService(startIntent);
		DataProviderService service = ((DataProviderService.LocalBinder)binder).getService();
		
		AndroidMock.expect(service.onGetObservedByDate("74861")).andReturn(observedRequest);
		AndroidMock.replay(mObservedRepository);
		
		service.onGetObservedByDate("74861");
		AndroidMock.verify(mObservedRepository);
	}
	
	
	@Override
	public void tearDown()throws Exception{
		super.tearDown();
	}
}
