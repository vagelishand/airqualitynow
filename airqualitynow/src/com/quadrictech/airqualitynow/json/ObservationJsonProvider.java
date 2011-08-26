package com.quadrictech.airqualitynow.json;

import java.io.IOException;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

import com.quadrictech.airqualitynow.model.IObservationWrapper;
import com.quadrictech.airqualitynow.model.ObservationWrapper;
import com.quadrictech.airqualitynow.utils.DateUtil;

public class ObservationJsonProvider implements IObservationJsonProvider {

	public IObservationWrapper parseJson(ObjectMapper mapper, String json) throws JsonParseException, JsonMappingException, IOException {
        mapper.setDateFormat(DateUtil.getDateFormat(DateUtil.DATE_FORMAT));
        //TODO use apache commons string.utils 
        json = json.replace("False", "false");
        json = json.replace("True", "true");
		ObservationWrapper observedData = mapper.readValue(json, ObservationWrapper.class);
			
		return observedData;
	}

}
