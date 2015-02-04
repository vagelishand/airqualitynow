package com.devterous.aqn.robo;


import android.view.View;
import android.widget.ListView;

import com.google.inject.AbstractModule;
import com.google.inject.TypeLiteral;
import com.devterous.aqn.json.ForecastJsonProvider;
import com.devterous.aqn.json.IForecastJsonProvider;
import com.devterous.aqn.model.ForecastWrapper;
import com.devterous.aqn.model.IForecastWrapper;
import com.devterous.aqn.presenter.IObservationPresenter;
import com.devterous.aqn.presenter.ObservationPresenter;
import com.devterous.aqn.presenter.ReportingAreaListPresenter;
import com.devterous.aqn.presenter.ForecastPresenter;
import com.devterous.aqn.presenter.IReportingAreaListPresenter;
import com.devterous.aqn.presenter.IForecastPresenter;
import com.devterous.aqn.view.IObservationView;
import com.devterous.aqn.view.ObservationView;
import com.devterous.aqn.view.ReportingAreaListView;
import com.devterous.aqn.view.ForecastView;
import com.devterous.aqn.view.IReportingAreaListView;
import com.devterous.aqn.view.IForecastView;

public class AirQualityNowApplicationModule extends AbstractModule{

	public AirQualityNowApplicationModule(){}
	
	@SuppressWarnings("unchecked")
	@Override
	protected void configure() {
        bind(IObservationPresenter.class).to(ObservationPresenter.class);
        bind(IObservationView.class ).to(ObservationView.class);
		bind(IForecastJsonProvider.class).to(ForecastJsonProvider.class);
		bind(IForecastWrapper.class).to(ForecastWrapper.class);
        bind(IForecastPresenter.class).to(ForecastPresenter.class);
        bind(IForecastView.class).to(ForecastView.class);
		bind(IReportingAreaListPresenter.class).to(ReportingAreaListPresenter.class);
		bind(IReportingAreaListView.class).to(ReportingAreaListView.class);
	}

}
