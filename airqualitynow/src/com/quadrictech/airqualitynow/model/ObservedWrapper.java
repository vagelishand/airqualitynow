package com.quadrictech.airqualitynow.model;

import java.util.List;

public class ObservedWrapper implements IObservedWrapper {
	List<Observed> mObserved;
	
	public List<Observed> getObserved() {
		return mObserved;
	}

	public void setObserved(List<Observed> observed) {
		mObserved = observed;
	}

}