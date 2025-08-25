package com.ucorp.catalog.dto;

import java.io.Serializable;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.ucorp.catalog.annotaion.ThisLogArg;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@ThisLogArg
@Data
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class PublisherCreateDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4383801395841698514L;

	@NotBlank(message = "publisher.must.not.blank")
	private String publisherName;
	
	@NotBlank(message = "compay.must.not.blank")
	private String companyName;	
	
	private String address;
}