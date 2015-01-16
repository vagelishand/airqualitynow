package com.devterous.aqn.model;

import java.util.List;

public class ObservationWrapper implements IObservationWrapper {
	List<Observation> mObservation;
	
	public ObservationWrapper(){
	}
	
	public List<Observation> getObserved() {
		return mObservation;
	}

	public void setObserved(List<Observation> observed) {
		mObservation = observed;
	}

}
