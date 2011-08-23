package com.quadrictech.airqualitynow.test.service;

import java.text.ParseException;
import java.util.Date;

import com.google.android.testing.mocking.AndroidMock;
import com.google.android.testing.mocking.UsesMocks;
import com.quadrictech.airqualitynow.db.IForecastRepository;
import com.quadrictech.airqualitynow.db.IObservedRepository;
import com.quadrictech.airqualitynow.db.IReportingAreaRepository;
import com.quadrictech.airqualitynow.db.callback.IDataRequestCallback;
import com.quadrictech.airqualitynow.model.Forecast;
import com.quadrictech.airqualitynow.model.Observed;
import com.quadrictech.airqualitynow.model.ReportingArea;
import com.quadrictech.airqualitynow.service.DataProviderService;
import com.quadrictech.airqualitynow.utils.DateUtil;

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
		IDataRequestCallback<Forecast> forecastRequest = null;
		mForecastRepository = AndroidMock.createMock(IForecastRepository.class);
		IBinder binder = bindService(startIntent);
		DataProviderService service = ((DataProviderService.LocalBinder)binder).getService();
		service.initialize(mForecastRepository);
		
		AndroidMock.expect(service.onGetAllForecasts()).andReturn(forecastRequest);
		AndroidMock.replay(mForecastRepository);
		
		service.onGetAllForecasts();
		
		AndroidMock.verify(mForecastRepository);
		
	}

	@UsesMocks(IReportingAreaRepository.class)
	@MediumTest
	public void testGetReportingArea(){
		IDataRequestCallback<ReportingArea> reportingAreaRequest = null;
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
	public void testGetObservedByDate() throws ParseException{
		IDataRequestCallback<Observed> observedRequest = null;
		mObservedRepository = AndroidMock.createMock(IObservedRepository.class);
		IBinder binder = bindService(startIntent);
		DataProviderService service = ((DataProviderService.LocalBinder)binder).getService();
		service.initialize(mObservedRepository);
		Date date = DateUtil.getDate("04/05/2010", "dd/MM/yyyy");
		
		AndroidMock.expect(service.onGetObservedByDate(date)).andReturn(observedRequest);
		AndroidMock.replay(mObservedRepository);
		
		service.onGetObservedByDate(date);
		AndroidMock.verify(mObservedRepository);
	}
	
	
	@Override
	public void tearDown()throws Exception{
		super.tearDown();
	}
}
