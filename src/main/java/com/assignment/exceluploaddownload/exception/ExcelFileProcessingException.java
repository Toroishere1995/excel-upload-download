package com.assignment.exceluploaddownload.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.UNSUPPORTED_MEDIA_TYPE)
public class ExcelFileProcessingException extends RuntimeException {

	public ExcelFileProcessingException(String message) {
		super(message);
	}
}
