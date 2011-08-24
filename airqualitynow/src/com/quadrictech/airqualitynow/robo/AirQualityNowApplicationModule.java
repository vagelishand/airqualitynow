package com.quadrictech.airqualitynow.robo;


import android.view.View;

import com.google.inject.AbstractModule;
import com.google.inject.TypeLiteral;
import com.quadrictech.airqualitynow.json.ForecastJsonProvider;
import com.quadrictech.airqualitynow.json.IForecastJsonProvider;
import com.quadrictech.airqualitynow.model.ForecastWrapper;
import com.quadrictech.airqualitynow.model.IForecastWrapper;
import com.quadrictech.airqualitynow.presenter.IObservedPresenter;
import com.quadrictech.airqualitynow.presenter.ObservedPresenter;
import com.quadrictech.airqualitynow.presenter.ReportingAreaListPresenter;
import com.quadrictech.airqualitynow.presenter.ForecastPresenter;
import com.quadrictech.airqualitynow.presenter.IReportingAreaListPresenter;
import com.quadrictech.airqualitynow.presenter.IForecastPresenter;
import com.quadrictech.airqualitynow.view.IObservedView;
import com.quadrictech.airqualitynow.view.ObservedView;
import com.quadrictech.airqualitynow.view.ReportingAreaListView;
import com.quadrictech.airqualitynow.view.ForecastView;
import com.quadrictech.airqualitynow.view.IReportingAreaListView;
import com.quadrictech.airqualitynow.view.IForecastView;

public class AirQualityNowApplicationModule extends AbstractModule{

	@SuppressWarnings("unchecked")
	@Override
	protected void configure() {
		bind(new TypeLiteral<IObservedPresenter<View>>(){}).to((Class<? extends IObservedPresenter<View>>) ObservedPresenter.class);
		bind(new TypeLiteral<IObservedView<View>>(){}).to(ObservedView.class);
		bind(IForecastJsonProvider.class).to(ForecastJsonProvider.class);
		bind(IForecastWrapper.class).to(ForecastWrapper.class);
		bind(new TypeLiteral <IForecastPresenter<View>>(){}).to((Class<? extends IForecastPresenter<View>>) ForecastPresenter.class);
		bind(new TypeLiteral<IForecastView<View>>(){}).to(ForecastView.class);
		bind(IReportingAreaListPresenter.class).to(ReportingAreaListPresenter.class);
		bind(IReportingAreaListView.class).to(ReportingAreaListView.class);
	}

}
