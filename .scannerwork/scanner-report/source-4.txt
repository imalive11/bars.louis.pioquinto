package com.accenture.bars.controller;

import org.springframework.http.HttpStatus;

public class BarsErrorResponse {
	
	private String timeStamp;
	private int status;
	private HttpStatus error;
	private String message;
	private String path;
	
	public BarsErrorResponse() {
	
	}


	public BarsErrorResponse(String timeStamp, 
			int status, 
			HttpStatus error, 
			String message, 
			String path) {
		
		this.timeStamp = timeStamp;
		this.status = status;
		this.error = error;
		this.message = message;
		this.path = path;
	}


	public HttpStatus getError() {
		return error;
	}

	public void setError(HttpStatus error) {
		this.error = error;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getTimeStamp() {
		return timeStamp;
	}

	public void setTimeStamp(String timeStamp) {
		this.timeStamp = timeStamp;
	}

}
