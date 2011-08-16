package com.quadrictech.airqualitynow.inet.rest;

import com.google.api.client.googleapis.GoogleUrl;

public class AirNowUrl {
	public GoogleUrl url;
	private StringBuilder stringBuilder;

	public AirNowUrl(AirNowUrlParameter parameterObject) {
		stringBuilder = new StringBuilder(parameterObject.baseUrl);
		stringBuilder.append("?");
		addParameters(parameterObject);
		createGoogleUrl(stringBuilder.toString());
	}

	private void createGoogleUrl(String url){
		this.url = new GoogleUrl(url);
	}
	
	private void addParameters(AirNowUrlParameter parameterObject){
		addZipCode(parameterObject.zipCode);
		addDate(parameterObject.date);
		addFormat(parameterObject.format);
		addKey(parameterObject.key);
	}
	
	private void addZipCode(String zipCode){
		if(zipCode != null){
			stringBuilder.append("zipCode=" + zipCode);
		}
	}
	
	/**
	 * @param date in yyyy-mm-dd format
	 */
	private void addDate(String date){
		if(date != null){
			stringBuilder.append("&date=" + date);
		}
	}
	
	private void addFormat(String format){
		stringBuilder.append("&format=" + format);
	}
	
	private void addKey(String key) {
		stringBuilder.append("&key=" + key);
	}
}