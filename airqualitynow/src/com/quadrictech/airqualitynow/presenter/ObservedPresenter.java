package com.quadrictech.airqualitynow.presenter;

import java.text.ParseException;

import com.quadrictech.airqualitynow.db.callback.IDataRequestCallback;
import com.quadrictech.airqualitynow.model.Observed;
import com.quadrictech.airqualitynow.presenter.util.IGuiRunnable;
import com.quadrictech.airqualitynow.service.helper.DataProviderServiceHelper;
import com.quadrictech.airqualitynow.utils.DateUtil;
import com.quadrictech.airqualitynow.view.IObservedView;

import android.content.Context;
import android.view.View;
import android.widget.Toast;

public class ObservedPresenter implements IObservedPresenter<IObservedView<View>> {
	private Context mContext;
	private IObservedView<View> mObservedView;
	private int mCurrentReportingAreaId;
	
	public ObservedPresenter(){
		
	}

	public void initialize(PresenterInitializeParameter parameterObject) {
		mContext = parameterObject.observedView.getView().getContext();
		mObservedView = parameterObject.observedView;
		mCurrentReportingAreaId = parameterObject.reportingAreaId;
		initializeTable();
	}
	
	public void initializeTable() {
		try {
			DataProviderServiceHelper.getInstance().getObservedByReportingAreaId(mCurrentReportingAreaId, DateUtil.getDateObserved(), new HandleGetObservedById());
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}
	
	class HandleGetObservedById implements IGuiRunnable<IDataRequestCallback<Observed>>{
		IDataRequestCallback<Observed> callback;
		
		public void run() {
			if(callback.getErrorStatus()){
				Toast.makeText(mContext, callback.getErrorMessage(), Toast.LENGTH_SHORT).show();
			}
			else{
				mObservedView.setObservedTableValues(callback.getList());
			}			
		}

		@SuppressWarnings("unchecked")
		public void setCallback(IDataRequestCallback<?> callback) {
			this.callback = (IDataRequestCallback<Observed>) callback;
		}
		
	}
	
	public void onDestroy() {
		// TODO Auto-generated method stub
		
	}
}
