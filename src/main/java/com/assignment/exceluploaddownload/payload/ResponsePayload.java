package com.assignment.exceluploaddownload.payload;

public class ResponsePayload {
	
	private String status;
	
	private String code;
	
	private String message;
	
	private Object result;

	public ResponsePayload(String code, String message, Object result, String status) {
		// TODO Auto-generated constructor stub
		this.code = code;
		this.message = message;
		this.result = result;
		this.status = status;
	}

	public String getCode() {
		return code;
	}

	public String getMessage() {
		return message;
	}

	public Object getResult() {
		return result;
	}

	public String getStatus() {
		return status;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public void setResult(Object result) {
		this.result = result;
	}

	public void setStatus(String status) {
		this.status = status;
	}
}
