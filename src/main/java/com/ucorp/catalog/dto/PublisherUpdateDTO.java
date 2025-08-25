package com.ucorp.catalog.dto;

import java.io.Serializable;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import lombok.Data;

@Data
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class PublisherUpdateDTO implements Serializable{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4583902438725907629L;

	private String publisherName;
	
	private String companyName;
	
	
	private String address;
}
