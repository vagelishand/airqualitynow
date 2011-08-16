package com.quadrictech.airqualitynow.inet.rest;

/**
 * Class to hold Rest Url and parameters 
 * @author Art
 *
 */
public class AirNowUrlParameter {
	public String baseUrl;
	public String zipCode;
	public String date;
	public String format;
	public String key;

	/**
	 * Parameter list
	 * 
	 * @param baseUrl
	 * @param zipCode
	 * @param date
	 * @param format
	 * @param key
	 */
	public AirNowUrlParameter(String baseUrl, String zipCode, String date,
			String format, String key) {
		this.baseUrl = baseUrl;
		this.zipCode = zipCode;
		this.date = date;
		this.format = format;
		this.key = key;
	}
}