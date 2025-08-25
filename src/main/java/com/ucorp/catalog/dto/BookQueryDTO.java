package com.ucorp.catalog.dto;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class BookQueryDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1478235975446737383L;

	private Long id;
	
	private String bookId;
	
	private String bookTitle;
	
	private String publisherName;

	private String description;
}
