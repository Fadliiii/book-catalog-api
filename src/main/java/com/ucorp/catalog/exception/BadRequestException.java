package com.ucorp.catalog.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST )
public class BadRequestException extends RuntimeException {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2124994403466283962L;

	public BadRequestException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

}
