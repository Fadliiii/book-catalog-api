package com.ucorp.catalog.dto;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AuthorQueryDTO implements Serializable{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3836597521331393401L;

	private Long bookId;

	private String authorName;
}
