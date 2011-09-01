package com.quadrictech.airqualitynow.test.presenter;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;


import roboguice.test.RoboUnitTestCase;
import android.test.suitebuilder.annotation.MediumTest;
import android.widget.ListView;

import com.google.android.testing.mocking.AndroidMock;
import com.google.android.testing.mocking.UsesMocks;
import com.quadrictech.airqualitynow.db.callback.IDataRequestCallback;
import com.quadrictech.airqualitynow.model.Forecast;
import com.quadrictech.airqualitynow.presenter.ReportingAreaListPresenter;
import com.quadrictech.airqualitynow.robo.AirQualityNowApplication;
import com.quadrictech.airqualitynow.service.helper.IDataProviderServiceHelper;
import com.quadrictech.airqualitynow.test.db.ForecastDataHelper;
import com.quadrictech.airqualitynow.view.IReportingAreaListView;

public class ForecastListPresenterTest<T> extends RoboUnitTestCase<AirQualityNowApplication> {
	
	private ReportingAreaListPresenter mPresenter;
	private IReportingAreaListView<ListView> mView;
	private IDataProviderServiceHelper mDataProviderServiceHelper;
	private ForecastDataHelper<Forecast> mDataHelper;
	IDataRequestCallback<Forecast> mCallback;
	List<Forecast> mForecasts;
	
	@SuppressWarnings("unchecked")
	public void setUp()throws ParseException{
				
		mView =  AndroidMock.createMock(IReportingAreaListView.class);
		mDataHelper = new ForecastDataHelper<Forecast>(Forecast.class);
		mCallback = AndroidMock.createMock(IDataRequestCallback.class);
		mForecasts = mDataHelper.getList();
		mPresenter = new ReportingAreaListPresenter(mView, mDataProviderServiceHelper, this.getInstrumentation().getContext());
	}
	
	@UsesMocks(IDataRequestCallback.class)
	@MediumTest
	public void testInitializeListWhenDataReceivedFromService() throws SQLException{

		mCallback.onResponseReceived(mForecasts);		
		AndroidMock.expect(mCallback.getErrorStatus()).andReturn(false);
		AndroidMock.expect(mCallback.getList()).andReturn(mForecasts);
		AndroidMock.replay(mCallback);
		
		mCallback.onResponseReceived(mForecasts);
		//mPresenter.handleForecasts(mCallback);
		AndroidMock.verify(mCallback);
	}
	
	@MediumTest
	public void testInitializeListWithErrorReturnedFromService(){
		Throwable e = new Throwable("Test Error");
		mCallback.onError(e);
		AndroidMock.expect(mCallback.getErrorStatus()).andReturn(true);
		AndroidMock.expect(mCallback.getErrorMessage()).andReturn("Test Error");
		AndroidMock.replay(mCallback);
		
		//mPresenter.handleForecasts(mCallback);
		mCallback.onError(e);
		AndroidMock.verify(mCallback);
	}
	
	
}
