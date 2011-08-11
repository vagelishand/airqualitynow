package com.quadrictech.airqualitynow.robo;


import android.view.View;

import com.google.inject.AbstractModule;
import com.google.inject.TypeLiteral;
import com.quadrictech.airqualitynow.db.ForecastRepository;
import com.quadrictech.airqualitynow.db.IForecastRepository;
import com.quadrictech.airqualitynow.presenter.ForecastListPresenter;
import com.quadrictech.airqualitynow.presenter.ForecastPresenter;
import com.quadrictech.airqualitynow.presenter.IForecastListPresenter;
import com.quadrictech.airqualitynow.presenter.IForecastPresenter;
import com.quadrictech.airqualitynow.view.ForecastListView;
import com.quadrictech.airqualitynow.view.ForecastView;
import com.quadrictech.airqualitynow.view.IForecastListView;
import com.quadrictech.airqualitynow.view.IForecastView;

public class AirQualityNowApplicationModule extends AbstractModule{

	@Override
	protected void configure() {
		bind(new TypeLiteral <IForecastPresenter<View>>(){}).to((Class<? extends IForecastPresenter<View>>) ForecastPresenter.class);
		bind(new TypeLiteral<IForecastView<View>>(){}).to(ForecastView.class);
		bind(IForecastListPresenter.class).to(ForecastListPresenter.class);
		bind(IForecastListView.class).to(ForecastListView.class);
		bind(IForecastRepository.class).to(ForecastRepository.class);
	}

}
