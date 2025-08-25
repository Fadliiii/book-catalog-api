package com.ucorp.catalog.dto;

import java.io.Serializable;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import lombok.Data;

@Data
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class RoleUserResponDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2592983024599661451L;
	private Long id;
	private String name;
}
