package com.quadrictech.airqualitynow.model.util;

import java.util.ArrayList;
import java.util.List;

import com.quadrictech.airqualitynow.model.Forecast;

public class ForecastUtil implements IForecastUtil {

	public List<Forecast> getFirstTwoForecastRecords(List<Forecast> forecasts, String particle) {
		
		List<Forecast> filteredForecasts = new ArrayList<Forecast>();
		
		for(Forecast forecast: forecasts){
			if(forecast.Pollutant.Name.compareTo(particle) == 0){
				filteredForecasts.add(forecast);
				
				if(filteredForecasts.size() == 2){
					break;
				}
			}
		}
		
		if(filteredForecasts.size() == 1){
			Forecast f = new Forecast();
			f.AQI = -1;
			f.Pollutant = filteredForecasts.get(0).Pollutant;
			filteredForecasts.add(f);
		}		
		
		return filteredForecasts;
	}

}
