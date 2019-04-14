package com.assignment.exceluploaddownload.exception.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.multipart.MultipartException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.assignment.exceluploaddownload.constants.Constants;
import com.assignment.exceluploaddownload.exception.ExcelFileNotFoundException;
import com.assignment.exceluploaddownload.exception.ExcelFileProcessingException;
import com.assignment.exceluploaddownload.payload.ResponsePayload;

/**
 * Custom exception handler for creating response of runtime errors.
 * 
 * @author ayushsaxena
 *
 */
@ControllerAdvice
public class CustomizedResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

	/**
	 * Method for handling resource exception.
	 * 
	 * @param exception
	 * @return
	 */
	@ExceptionHandler(value = ExcelFileNotFoundException.class)
	public ResponseEntity<ResponsePayload> resourceException(ExcelFileNotFoundException exception) {
		ResponsePayload payload = new ResponsePayload(Constants.STATUS_CODE_404, exception.getMessage(), null,
				Constants.RESOURCE_NOT_FOUND);
		return new ResponseEntity<>(payload, HttpStatus.NOT_FOUND);
	}

	/**
	 * Method for handling excel file processing exceptions.
	 * 
	 * @param exception
	 * @return
	 */
	@ExceptionHandler(value = ExcelFileProcessingException.class)
	public ResponseEntity<ResponsePayload> excelFileSupportException(ExcelFileProcessingException exception) {
		ResponsePayload payload = new ResponsePayload(Constants.STATUS_CODE_415, exception.getMessage(), null,
				Constants.UNSUPPORTED_FILE);
		return new ResponseEntity<>(payload, HttpStatus.UNSUPPORTED_MEDIA_TYPE);
	}

	/**
	 * Method for handling file size exception.
	 * 
	 * @param exception
	 * @return
	 */
	@ExceptionHandler(value = MultipartException.class)
	public ResponseEntity<ResponsePayload> fileSizeException(MultipartException exception) {
		ResponsePayload payload = new ResponsePayload(Constants.STATUS_CODE_415, Constants.NO_FILE_OR_INVALID_SIZE,
				null, "UNSUPPORTED FILE TYPE");
		return new ResponseEntity<>(payload, HttpStatus.UNSUPPORTED_MEDIA_TYPE);
	}
}
