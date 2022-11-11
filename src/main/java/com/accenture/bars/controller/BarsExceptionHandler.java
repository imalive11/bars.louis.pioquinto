package com.accenture.bars.controller;

import java.text.SimpleDateFormat;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.accenture.bars.exception.BarsException;

@ControllerAdvice
public class BarsExceptionHandler {
	
	private static final SimpleDateFormat sdf2 = 
			new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX");
	private static String timeStamp = 
			sdf2.format(System.currentTimeMillis());
	
	@ExceptionHandler
	public ResponseEntity<BarsErrorResponse> handleException(BarsException e) {
	
		BarsErrorResponse error = new BarsErrorResponse(
				timeStamp,
				HttpStatus.BAD_REQUEST.value(), 
				HttpStatus.BAD_REQUEST,
				e.getMessage(),
				"/bars");
		
		return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
	}
}
