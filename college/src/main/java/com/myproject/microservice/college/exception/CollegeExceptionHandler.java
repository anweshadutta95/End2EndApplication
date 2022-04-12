package com.myproject.microservice.college.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class CollegeExceptionHandler {

	Logger logger = LoggerFactory.getLogger(CollegeExceptionHandler.class);
	
	@ExceptionHandler
	public ResponseEntity<ErrorResponse> handleNotFoundException(CollegeNotFoundException e) {
		ErrorResponse response = new ErrorResponse(HttpStatus.NOT_FOUND.value(), e.getMessage(), System.currentTimeMillis());
		logger.error(e.getMessage());
		return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler
	public ResponseEntity<ErrorResponse> handleAllExceptions(Exception e) {
		ErrorResponse response = new ErrorResponse(HttpStatus.BAD_REQUEST.value(), e.getMessage(), System.currentTimeMillis());
		return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
	}
}
