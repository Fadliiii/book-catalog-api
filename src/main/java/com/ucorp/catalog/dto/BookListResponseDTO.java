package com.ucorp.catalog.dto;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import lombok.Data;

@Data
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class BookListResponseDTO implements Serializable {/**
	 * 
	 */
	private static final long serialVersionUID = -1095307577196148744L;

	
	private String id;
	
	private String title;
	
	private String description;
	
	private String pubsliherName;
	
	private List<String> categoryCode;
	
	private List<String> authorName;
}
