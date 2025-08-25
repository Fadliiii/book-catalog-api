package com.ucorp.catalog.dto;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class CategoryQueryDTO implements Serializable  {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4941494527863931057L;

	private Long bookId;
	
	private String categoryCode;
}
