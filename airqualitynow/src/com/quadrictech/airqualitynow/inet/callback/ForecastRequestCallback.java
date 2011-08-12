package com.quadrictech.airqualitynow.inet.callback;

import java.io.IOException;
import java.text.SimpleDateFormat;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

import android.util.Log;

import com.google.api.client.http.HttpResponse;
import com.quadrictech.airqualitynow.json.ForecastWrapper;
import com.quadrictech.airqualitynow.model.Forecast;

public class ForecastRequestCallback implements IRequestCallback {

	public ForecastRequestCallback(){
		
	}
	
	public void onError(Throwable exception) {
		exception.printStackTrace();
	}

	public void onResponseReceived(HttpResponse response) {
		ObjectMapper mapper = new ObjectMapper();
		try {
			String json = response.parseAsString();
			Log.d("callback", json);
			final String DATE_FORMAT = "yyyy/MM/dd HH:mm:ss a";
			SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);
	        mapper.setDateFormat(sdf);
	        //TODO use apache commons string.utils 
	        json = json.replace("False", "false");
	        json = json.replace("True", "true");
			ForecastWrapper forecastData = mapper.readValue(json, ForecastWrapper.class);
			
			for(Forecast data: forecastData.forecasts){
				Log.d("callback", data.DateForecast);
			}
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
	}

}
