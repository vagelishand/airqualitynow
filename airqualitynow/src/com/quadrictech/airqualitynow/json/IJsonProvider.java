package com.quadrictech.airqualitynow.json;

import org.codehaus.jackson.map.ObjectMapper;

public interface IJsonProvider<T> {
	public T parseJson(ObjectMapper mapper, String json);
}
