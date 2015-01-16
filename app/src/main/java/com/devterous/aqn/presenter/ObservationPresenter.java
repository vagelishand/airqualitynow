package com.devterous.aqn.presenter;

import java.text.ParseException;
import com.devterous.aqn.model.Observation;
import com.devterous.aqn.presenter.handlers.ObservationGetById;
import com.devterous.aqn.presenter.util.ObservedArrayAdapter;
import com.devterous.aqn.service.helper.DataProviderServiceHelper;
import com.devterous.aqn.utils.DateUtil;
import com.devterous.aqn.view.IObservationView;

import android.content.Context;
import android.widget.ListView;

public class ObservationPresenter implements IObservationPresenter<IObservationView<ListView>> {
	public  Context mContext;
	public IObservationView<ListView> mObservedView;
	public int mCurrentReportingAreaId;
	public String mZipCode;
	public ObservedArrayAdapter mArrayAdapter;
	
	public ObservationPresenter(){
		
	}

	public void initialize(PresenterInitializeParameter parameterObject) {
		mContext = parameterObject.observedView.getView().getContext();
		mObservedView = parameterObject.observedView;
		mCurrentReportingAreaId = parameterObject.reportingAreaId;
		mZipCode = parameterObject.zipCode;
		initializeTable();
	}
	
	public void initializeTable() {
		try {
			DataProviderServiceHelper.getInstance().getObservationsByReportingAreaId(mCurrentReportingAreaId, 
					                                                                 DateUtil.getDateObserved(), 
					                                                                 new ObservationGetById(this));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}
		
	public void onDestroy() {
		// TODO Auto-generated method stub
	}

	public void onSetObservationTableValues(Observation observation) throws ParseException {
		mObservedView.setObservationTableValues(observation);
	}
}
