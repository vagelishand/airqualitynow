package com.quadrictech.airqualitynow.json;

import java.io.IOException;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

import com.quadrictech.airqualitynow.model.IReportingAreaWrapper;
import com.quadrictech.airqualitynow.model.ReportingAreaWrapper;
import com.quadrictech.airqualitynow.utils.DateUtil;

public class ReportingAreaJsonProvider implements IReportingAreaJsonProvider {

	public ReportingAreaJsonProvider(){
		
	}
	
	public IReportingAreaWrapper parseJson(ObjectMapper mapper, String json) throws JsonParseException, JsonMappingException, IOException {
		mapper.setDateFormat(DateUtil.getDateFormat(DateUtil.DATE_FORMAT));
        //TODO use apache commons string.utils 
        json = json.replace("False", "false");
        json = json.replace("True", "true");
		
        ReportingAreaWrapper reportingAreaData = mapper.readValue(json, ReportingAreaWrapper.class);
			
		return reportingAreaData;
	}

}
