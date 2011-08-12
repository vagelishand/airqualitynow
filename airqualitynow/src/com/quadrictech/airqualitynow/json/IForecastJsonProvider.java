package com.quadrictech.airqualitynow.json;

import org.codehaus.jackson.map.ObjectMapper;

public interface IForecastJsonProvider extends IJsonProvider{
	public ForecastWrapper parseForecastJson(ObjectMapper mapper, String json);
}
