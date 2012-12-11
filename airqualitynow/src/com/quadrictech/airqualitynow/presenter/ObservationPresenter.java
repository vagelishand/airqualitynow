package com.quadrictech.airqualitynow.presenter;

import java.text.ParseException;
import com.quadrictech.airqualitynow.model.Observation;
import com.quadrictech.airqualitynow.presenter.handlers.ObservationGetById;
import com.quadrictech.airqualitynow.presenter.util.ObservedArrayAdapter;
import com.quadrictech.airqualitynow.service.helper.DataProviderServiceHelper;
import com.quadrictech.airqualitynow.settings.AppPreferences;
import com.quadrictech.airqualitynow.settings.IPreferences;
import com.quadrictech.airqualitynow.utils.DateUtil;
import com.quadrictech.airqualitynow.view.IObservationView;

import android.content.Context;
import android.widget.ListView;
import android.widget.Toast;

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
