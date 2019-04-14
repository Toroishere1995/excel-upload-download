package com.assignment.exceluploaddownload.constants;

import java.util.Collections;
import java.util.Hashtable;
import java.util.Map;

/**
 * Class for holding constants.
 * 
 * @author ayushsaxena
 *
 */
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

	public static final String INVALID_HEADER = "Headers are not valid text.";

	public static final String CURROPT_FILE = "File not found or maybe it is corrupt";

	public static final String HEADER_NOT_EXIST = "Header specified does not exist";

	public static final String INVALID_FILE_ID = "Id for files is incorrect";
	
	public static final String STATUS_CODE_404="404";
	
	public static final String STATUS_CODE_415="415";
	
	public static final String STATUS_CODE_201="201";
	
	public static final String RESOURCE_NOT_FOUND="RESOURCE NOT FOUND";
	
	public static final String NO_FILE_OR_INVALID_SIZE="Either no file or file size issue.";
	
	public static final String UNSUPPORTED_FILE="UNSUPPORTED FILE TYPE";
	
	public static final String EXCEL_FILES_UPLOAD_SUCCESS="Excel Files uploaded successfully";
	
	public static final String ALLOWED_ORIGIN="http://localhost:4200";
}
