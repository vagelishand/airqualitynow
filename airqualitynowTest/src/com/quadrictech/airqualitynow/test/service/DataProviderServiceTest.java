package com.quadrictech.airqualitynow.test.service;

import java.text.ParseException;
import java.util.Date;

import com.google.android.testing.mocking.AndroidMock;
import com.google.android.testing.mocking.UsesMocks;
import com.quadrictech.airqualitynow.db.IForecastRepository;
import com.quadrictech.airqualitynow.db.IObservationRepository;
import com.quadrictech.airqualitynow.db.IReportingAreaRepository;
import com.quadrictech.airqualitynow.db.callback.IDataRequestCallback;
import com.quadrictech.airqualitynow.model.Forecast;
import com.quadrictech.airqualitynow.model.Observation;
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
	private IObservationRepository mObservedRepository;
	
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
		
		AndroidMock.expect(service.getAllForecasts()).andReturn(forecastRequest);
		AndroidMock.replay(mForecastRepository);
		
		service.getAllForecasts();
		
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
		
		AndroidMock.expect(service.getAllReportingAreas()).andReturn(reportingAreaRequest);
		AndroidMock.replay(mReportingAreaRepository);
		
		service.getAllReportingAreas();
		
		AndroidMock.verify(mReportingAreaRepository);
	}
	
	@UsesMocks(IObservationRepository.class)
	@MediumTest
	public void testGetObservedByDate() throws ParseException{
		IDataRequestCallback<Observation> observedRequest = null;
		mObservedRepository = AndroidMock.createMock(IObservationRepository.class);
		IBinder binder = bindService(startIntent);
		DataProviderService service = ((DataProviderService.LocalBinder)binder).getService();
		service.initialize(mObservedRepository);
		Date date = DateUtil.getDate("04/05/2010", "dd/MM/yyyy");
		
		//AndroidMock.expect(service.getObservedByReportingAreaId(date)).andReturn(observedRequest);
		AndroidMock.replay(mObservedRepository);
		
		//service.getObservedByReportingAreaId(date);
		AndroidMock.verify(mObservedRepository);
	}
	
	
	@Override
	public void tearDown()throws Exception{
		super.tearDown();
	}
}
