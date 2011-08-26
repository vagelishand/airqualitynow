package com.quadrictech.airqualitynow.model;

import java.util.List;

public interface IObservationWrapper {
	public List<Observation> getObserved();
	public void setObserved(List<Observation> observed);
}
