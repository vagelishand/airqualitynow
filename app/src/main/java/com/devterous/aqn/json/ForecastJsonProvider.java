package com.devterous.aqn.json;

import java.io.IOException;
import java.util.List;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;

import com.devterous.aqn.model.Forecast;
import com.devterous.aqn.model.ForecastWrapper;
import com.devterous.aqn.model.IForecastWrapper;
import com.devterous.aqn.utils.DateUtil;

public class ForecastJsonProvider implements IForecastJsonProvider {
		
	public ForecastJsonProvider(){
		
	}

	public IForecastWrapper parseJson(ObjectMapper mapper, String json) throws JsonParseException, JsonMappingException, IOException{
		
        mapper.setDateFormat(DateUtil.getDateFormat(DateUtil.DATE_FORMAT));
        //TODO use apache commons string.utils 
        json = json.replace("False", "false");
        json = json.replace("True", "true");
        json = json.replace(" \"", "\"");

        List<Forecast> forecastList = mapper.readValue(json, new TypeReference<List<Forecast>>(){});
		ForecastWrapper forecastWrapper = new ForecastWrapper();
		forecastWrapper.setForecast(forecastList);

		return forecastWrapper;
	}

}
