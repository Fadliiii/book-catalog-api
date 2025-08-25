package com.ucorp.catalog.dto;

import org.hibernate.validator.cfg.context.PropertyTarget;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)//Ini Untuk mengubah Naming menjadi Snake Case Pad JSon
public class BookUpadateRequestDto {

	@NotBlank
	private String bookTitle;

	@JsonProperty("synopsis")
	private String description;
	
	
}
