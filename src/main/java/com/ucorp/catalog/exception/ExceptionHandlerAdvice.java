package com.ucorp.catalog.exception;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.ucorp.catalog.dto.ErrorResponseDTO;
import com.ucorp.catalog.enums.ErrorCode;

import jakarta.servlet.ServletRequest;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;

@ControllerAdvice
public class ExceptionHandlerAdvice extends ResponseEntityExceptionHandler {
	
	
	@ExceptionHandler(ResourceNotFoundException.class)
	protected ResponseEntity<ErrorResponseDTO> handlerResourceNotFoundException(ResourceNotFoundException ex,
			WebRequest request) {
		List<String> details = new ArrayList<>();
		details.add(ex.getMessage());
		String path = ((ServletWebRequest)request).getRequest().getRequestURI();
		ErrorResponseDTO errorResponseDTO = ErrorResponseDTO.of("Data NOT FOUND", details, ErrorCode.DATA_NOT_FOUND,
				HttpStatus.BAD_REQUEST,path);
		return ResponseEntity.badRequest().body(errorResponseDTO);
	}

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatusCode status, WebRequest request) {

		List<String> details = new ArrayList<>();

		for (ObjectError error : ex.getAllErrors()) {
			details.add(error.getDefaultMessage());
		}

		String path = ((ServletWebRequest)request).getRequest().getRequestURI();
		
		ErrorResponseDTO errorResponse = ErrorResponseDTO.of("Invalid data", details, ErrorCode.INVALID_DATA,
				HttpStatus.BAD_REQUEST,path);

		return ResponseEntity.badRequest().body(errorResponse);
	}

}
