package com.quadrictech.airqualitynow.presenter;

import java.text.ParseException;

import com.quadrictech.airqualitynow.R;
import com.quadrictech.airqualitynow.db.callback.IDataRequestCallback;
import com.quadrictech.airqualitynow.model.Observation;
import com.quadrictech.airqualitynow.presenter.util.IGuiRunnable;
import com.quadrictech.airqualitynow.presenter.util.ObservedArrayAdapter;
import com.quadrictech.airqualitynow.service.helper.DataProviderServiceHelper;
import com.quadrictech.airqualitynow.utils.DateUtil;
import com.quadrictech.airqualitynow.view.IObservationView;

import android.content.Context;
import android.widget.ListView;
import android.widget.Toast;

public class ObservationPresenter implements IObservationPresenter<IObservationView<ListView>> {
	private Context mContext;
	private IObservationView<ListView> mObservedView;
	private int mCurrentReportingAreaId;
	private ObservedArrayAdapter mArrayAdapter;
	
	public ObservationPresenter(){
		
	}

	public void initialize(PresenterInitializeParameter parameterObject) {
		mContext = parameterObject.observedView.getView().getContext();
		mObservedView = parameterObject.observedView;
		mCurrentReportingAreaId = parameterObject.reportingAreaId;
		initializeTable();
	}
	
	public void initializeTable() {
		try {
			DataProviderServiceHelper.getInstance().getObservationsByReportingAreaId(mCurrentReportingAreaId, DateUtil.getDateObserved(), new HandleGetObservedById());
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}
	
	class HandleGetObservedById implements IGuiRunnable<IDataRequestCallback<Observation>>{
		IDataRequestCallback<Observation> callback;
		
		public void run() {
			if(callback.getErrorStatus()){
				Toast.makeText(mContext, callback.getErrorMessage(), Toast.LENGTH_SHORT).show();
			}
			else{
				try{
					if(callback.getList().size() > 0){
						mObservedView.setObservedTableValues(callback.getList().get(0));
					}
					mArrayAdapter = new ObservedArrayAdapter(mContext, R.id.observationList, callback.getList());
					
					mObservedView.setAdapter(mArrayAdapter);
				}
				catch(ParseException e){
					Toast.makeText(mContext, "Error parsing Date", Toast.LENGTH_SHORT).show();
				}
				
				
			}			
		}

		@SuppressWarnings("unchecked")
		public void setCallback(IDataRequestCallback<?> callback) {
			this.callback = (IDataRequestCallback<Observation>) callback;
		}
		
	}
	
	public void onDestroy() {
		// TODO Auto-generated method stub
		
	}
}
