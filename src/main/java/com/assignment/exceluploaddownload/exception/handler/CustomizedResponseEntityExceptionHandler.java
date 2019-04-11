package com.assignment.exceluploaddownload.exception.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.assignment.exceluploaddownload.exception.ExcelFileNotFoundException;
import com.assignment.exceluploaddownload.exception.ExcelFileProcessingException;
import com.assignment.exceluploaddownload.payload.ResponsePayload;

@ControllerAdvice
public class CustomizedResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(value = ExcelFileNotFoundException.class)
	public ResponseEntity<ResponsePayload> resourceException(ExcelFileNotFoundException exception) {
		ResponsePayload payload = new ResponsePayload("404", exception.getMessage(),
				null, "RESOURCE NOT FOUND");
		return new ResponseEntity<>(payload, HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(value = ExcelFileProcessingException.class)
	public ResponseEntity<ResponsePayload> excelFileSupportException(ExcelFileProcessingException exception){
		ResponsePayload payload = new ResponsePayload("415", exception.getMessage(),
				null, "UNSUPPORTED FILE TYPE");
		return new ResponseEntity<>(payload, HttpStatus.UNSUPPORTED_MEDIA_TYPE);
	}
}
