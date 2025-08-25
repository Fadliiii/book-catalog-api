package com.ucorp.catalog.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.springframework.http.HttpStatus;

import com.ucorp.catalog.enums.ErrorCode;

import lombok.Data;

@Data
public class ErrorResponseDTO implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -1893939146056438518L;

	private Date timestamp;
	
	private String message;
	
	private ErrorCode errorCode;
	
	private List<String>details;
	
  // ini balikannya angka // private int status;
   
   private HttpStatus status;
   
   private String path;

   
   public static ErrorResponseDTO of(final String massage,List<String> details, final ErrorCode errorCode,HttpStatus status,String path) {
	   return new ErrorResponseDTO(massage, errorCode, details, status,path);
   }


public ErrorResponseDTO(String message, ErrorCode errorCode, List<String> details, HttpStatus status,String path) {
	super();
	this.message = message;
	this.errorCode = errorCode;
	this.details = details;
	this.status = status;
	this.timestamp = new Date();
	this.path = path;
}
}
