package com.assignment.exceluploaddownload.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Custom Exception class for case when excel file not found.
 * 
 * @author ayushsaxena
 *
 */
@ResponseStatus(HttpStatus.NOT_FOUND)
public class ExcelFileNotFoundException extends RuntimeException {

	public ExcelFileNotFoundException(String message) {
		super(message);
	}

}
