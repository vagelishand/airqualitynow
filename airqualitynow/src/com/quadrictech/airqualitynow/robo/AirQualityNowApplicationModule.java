package com.quadrictech.airqualitynow.robo;


import com.google.inject.AbstractModule;
import com.quadrictech.airqualitynow.db.ForecastRepository;
import com.quadrictech.airqualitynow.db.IForecastRepository;
import com.quadrictech.airqualitynow.presenter.ForecastListPresenter;
import com.quadrictech.airqualitynow.presenter.IForecastListPresenter;
import com.quadrictech.airqualitynow.view.ForecastListView;
import com.quadrictech.airqualitynow.view.IForecastListView;

public class AirQualityNowApplicationModule extends AbstractModule{

	@Override
	protected void configure() {
		bind(IForecastListPresenter.class).to(ForecastListPresenter.class);
		bind(IForecastListView.class).to(ForecastListView.class);
		bind(IForecastRepository.class).to(ForecastRepository.class);
	}

}
