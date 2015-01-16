package com.devterous.aqn.json;

import java.io.IOException;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

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
		
		ForecastWrapper forecastData = mapper.readValue(json, ForecastWrapper.class);
			
		return forecastData;
	}

}
