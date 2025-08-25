package com.ucorp.catalog.dto;

import java.io.Serializable;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import lombok.Data;

@Data
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class AddressUpadateRequestDTO implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 5615977918545122621L;

	private Long id;
	
	private String streetName;
	
	private String cityName;
	
	private String zipCode;

}
