package com.moviereview.exception;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ErrorResponse {
	
	private String message;
	private LocalDateTime timestamp;
	private String description;
	
	

}