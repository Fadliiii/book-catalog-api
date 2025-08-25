package com.ucorp.catalog.dto;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import lombok.Data;

@Data
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class ResultPageResponDTO<T> implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4486965711832657700L;

	//kenapa generic biar bisa di parameterize
	private List<T> result; 
	
	//jumlah halaman
	private Integer page;
	
	//jumalh records yang bisa ditampilkan dalam satu query
	private Long elements;
}
