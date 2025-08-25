package com.ucorp.catalog.dto;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import lombok.Data;

@Data
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class UserDetailResponDTO implements Serializable {/**
	 * 
	 */
	private static final long serialVersionUID = -9111380655199527931L;
	
	private String name;

}
