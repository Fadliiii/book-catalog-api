package com.ucorp.catalog.dto;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class BookCreateRequestDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8883338543966770625L;

	@NotBlank
	private String bookTitle;

	@NotEmpty
	private List<String> authorIdList;
	
	@NotEmpty
	private List<String> categoryList;

	@NotEmpty
	private String publisherId;
	
	private String description;
	
}
