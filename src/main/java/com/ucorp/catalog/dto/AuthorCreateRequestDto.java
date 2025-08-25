package com.ucorp.catalog.dto;

import java.util.List;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.ucorp.catalog.validator.annotaion.ValidAuthorName;

import io.micrometer.common.lang.NonNull;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class AuthorCreateRequestDto {

	@ValidAuthorName
	@NotBlank
	private String authorName;
	
	@NonNull
	private Long birthDate;
	
	@NotEmpty
	private List<AddressCreateRequestDTO>addresses;
}
