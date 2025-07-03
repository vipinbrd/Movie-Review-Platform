package com.moviereview.exception;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class GlobalExceptionHandler {
	@ExceptionHandler(MovieNotFoundException.class)
	public ResponseEntity<ErrorResponse> movieNotFoundException(Exception  e, WebRequest webr) {

		ErrorResponse me = new ErrorResponse(e.getMessage(), LocalDateTime.now(), webr.getDescription(false));

		return new ResponseEntity<>(me, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorResponse> OtherException(Exception  e, WebRequest webr) {

		ErrorResponse me = new ErrorResponse(e.getMessage(), LocalDateTime.now(), webr.getDescription(false));

		return new ResponseEntity<>(me, HttpStatus.INTERNAL_SERVER_ERROR);
	}

}
