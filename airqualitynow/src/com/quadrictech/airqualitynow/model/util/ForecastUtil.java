package com.quadrictech.airqualitynow.model.util;

import java.util.ArrayList;
import java.util.List;

import com.quadrictech.airqualitynow.model.Forecast;
import com.quadrictech.airqualitynow.model.Pollutant;

public class ForecastUtil implements IForecastUtil {
	
	public List<Forecast> getFirstTwoForecastRecords(List<Forecast> forecasts, List<Pollutant> pollutants) {
		
		List<Forecast> filteredForecasts = new ArrayList<Forecast>();
		@SuppressWarnings("unused")
		Pollutant currentPollutant = null;
		int pollutantCnt = 0;
		
		for(Pollutant pollutant: pollutants){
			
			for(Forecast forecast: forecasts){
				currentPollutant = pollutant;
				
				if(forecast.ParameterName.compareTo(pollutant.Name) == 0){
					forecast.Pollutant = pollutant;
					filteredForecasts.add(forecast);
					pollutantCnt++;
					
					if(pollutantCnt == 2){
						pollutantCnt = 0;
						break;
					}
				}
			}
			
			if(pollutantCnt == 1){
				Forecast f = new Forecast();
				f.AQI = -1;
				f.Pollutant = pollutant;
				filteredForecasts.add(f);
				pollutantCnt = 0;
			}
		}
		
		return filteredForecasts;
	}

}
