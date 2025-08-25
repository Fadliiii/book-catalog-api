package com.ucorp.catalog.dto;

import java.io.Serializable;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class CreateUpdateRequestCategoryDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4184785486065955952L;

	@NotBlank
	private String code;
	
	@NotBlank
	private String name;
	
	private String description;
	
}
