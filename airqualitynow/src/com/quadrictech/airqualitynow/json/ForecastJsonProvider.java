package com.quadrictech.airqualitynow.json;

import java.io.IOException;
import java.text.SimpleDateFormat;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

public class ForecastJsonProvider implements IForecastJsonProvider {
		
	public ForecastJsonProvider(){
		
	}
	
	public ForecastWrapper parseForecastJson(ObjectMapper mapper, String json) {
		final String DATE_FORMAT = "yyyy/MM/dd HH:mm:ss a";
		SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);
        mapper.setDateFormat(sdf);
        //TODO use apache commons string.utils 
        json = json.replace("False", "false");
        json = json.replace("True", "true");
		try {
			ForecastWrapper forecastData = mapper.readValue(json, ForecastWrapper.class);
			
			return forecastData;
		} catch (JsonParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

}
