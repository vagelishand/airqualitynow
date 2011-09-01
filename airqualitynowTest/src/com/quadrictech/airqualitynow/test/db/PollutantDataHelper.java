package com.quadrictech.airqualitynow.test.db;

import java.util.ArrayList;
import java.util.List;

import com.quadrictech.airqualitynow.model.Pollutant;

public class PollutantDataHelper {
	
	public List<Pollutant> getList(){
		List<Pollutant>list = new ArrayList<Pollutant>(){
			private static final long serialVersionUID = 1L;

			{
				add(new Pollutant(1, "CO", "Carbon Monoxide"));
				add(new Pollutant(2, "NO2", "Nitrogen Dioxide"));
				add(new Pollutant(3, "OZONE", "Ozone"));
				add(new Pollutant(4, "PM10", "Particles(PM10)"));
				add(new Pollutant(5, "PM2.5", "Particles(PM2.5)"));
				add(new Pollutant(6, "SO2", "Sulfur Dioxide"));
			}
		};
		
		return list;
	}
	
	
} 
