package com.assignment.exceluploaddownload.payload;

public class ResponsePayload {

	private String code;

	private String message;

	private Object result;

	private String status;

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
