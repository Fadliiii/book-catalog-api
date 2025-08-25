package com.ucorp.catalog.util;

import java.util.List;

import org.springframework.data.domain.Sort;

import com.ucorp.catalog.dto.ResultPageResponDTO;

public class PaginationUtil {
	
	public static <T> ResultPageResponDTO<T> createResultPageDTO(List<T> dtos, Long totalElements,Integer Pages ){
		ResultPageResponDTO<T> result = new ResultPageResponDTO<T>();
		result.setPage(Pages);
		result.setElements(totalElements);
		result.setResult(dtos);
		return result;
	}
	
		public static Sort.Direction getSortBy(String sortBy){
			if(sortBy.equalsIgnoreCase("asc")) {
				return Sort.Direction.ASC;
			}else {
				return Sort.Direction.DESC;
			}
		}
}
