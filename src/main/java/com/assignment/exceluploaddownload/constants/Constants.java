package com.assignment.exceluploaddownload.constants;

import java.util.Collections;
import java.util.Hashtable;
import java.util.Map;

public class Constants {

	public static final Map<String, String> CODES_HASH;
	public static final String SUCCESS = "Success";
	public static final String ERROR = "ERROR";

	static {
		Hashtable<String, String> temp = new Hashtable<>();
		temp.put("500", "Server Error");
		temp.put("501", "Not Implemented");
		temp.put("404", "Not Found");
		temp.put("401", "Unauthorized Access");
		temp.put("201", "POST/PUT Successful");
		temp.put("200", "GET Successful");
		temp.put("400", "Bad Request");
		temp.put("204", "No Content");
		CODES_HASH = Collections.unmodifiableMap(temp);
	}

	public static final Map<String, String> CODES_MSG_HASH;

	static {
		Hashtable<String, String> tempMsg = new Hashtable<>();
		tempMsg.put("Server Error",
				"The server encountered an unexpected condition which prevented it from fulfilling the request.");
		tempMsg.put("Not Implemented",
				"The server does not support the functionality required to fulfill the request.");
		tempMsg.put("Not Found", "The server has not found anything matching the Request-URI. ");
		tempMsg.put("Unauthorized Access", "Invalid Credentials or Unauthorized Access");
		tempMsg.put("POST/PUT Successful", "CREATED");
		tempMsg.put("GET Successful", "OK");
		tempMsg.put("No Content",
				"The server has fulfilled the request but does not need to return an entity-body, and might want to return updated metainformation");
		tempMsg.put("Bad Request", "The request could not be understood by the server due to malformed syntax");
		CODES_MSG_HASH = Collections.unmodifiableMap(tempMsg);
	}

}
