package com.ucorp.catalog.dto;

import java.io.Serializable;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class CategoryListResponseDTO implements Serializable{/**
	 * 
	 */
	private static final long serialVersionUID = 5533900515124073749L;
	
	private String code;
	
	private String name;
	
	private String description;

}
