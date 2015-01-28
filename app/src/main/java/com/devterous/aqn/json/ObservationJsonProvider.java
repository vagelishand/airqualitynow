package com.devterous.aqn.json;

import java.io.IOException;
import java.util.List;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;

import com.devterous.aqn.model.Forecast;
import com.devterous.aqn.model.IObservationWrapper;
import com.devterous.aqn.model.Observation;
import com.devterous.aqn.model.ObservationWrapper;
import com.devterous.aqn.utils.DateUtil;

public class ObservationJsonProvider implements IObservationJsonProvider {

	public IObservationWrapper parseJson(ObjectMapper mapper, String json) throws JsonParseException, JsonMappingException, IOException {
        mapper.setDateFormat(DateUtil.getDateFormat(DateUtil.DATE_FORMAT));
        //TODO use apache commons string.utils 
        json = json.replace("False", "false");
        json = json.replace("True", "true");
        json = json.replace(" \"", "\"");

        List<Observation> observedList = mapper.readValue(json, new TypeReference<List<Observation>>(){});
        ObservationWrapper observationWrapper = new ObservationWrapper();
        observationWrapper.setObserved(observedList);

		return observationWrapper;
	}

}
