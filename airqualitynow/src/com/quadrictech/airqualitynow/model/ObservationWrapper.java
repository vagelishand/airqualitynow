package com.quadrictech.airqualitynow.model;

import java.util.List;

public class ObservationWrapper implements IObservedWrapper {
	List<Observation> mObserved;
	
	public ObservationWrapper(){
	}
	
	public List<Observation> getObserved() {
		return mObserved;
	}

	public void setObserved(List<Observation> observed) {
		mObserved = observed;
	}

}
