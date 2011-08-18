package com.quadrictech.airqualitynow.json;

import java.io.IOException;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

public interface IJsonProvider<T> {
	public T parseJson(ObjectMapper mapper, String json)throws JsonParseException, JsonMappingException, IOException;
}
