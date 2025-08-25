package com.ucorp.catalog.web;

import java.net.URI;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ucorp.catalog.dto.CategoryCreateUpdateRecordDTO;
import com.ucorp.catalog.dto.CategoryListResponseDTO;
import com.ucorp.catalog.dto.CreateUpdateRequestCategoryDTO;
import com.ucorp.catalog.dto.ResultPageResponDTO;
import com.ucorp.catalog.service.CategoryService;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;


@RestController
@AllArgsConstructor
public class CategoryResources {

	private final CategoryService categoryService;
	
	@PostMapping("v1/category")
	public ResponseEntity<Void>createUpdateCategory(@RequestBody @Valid CategoryCreateUpdateRecordDTO dto){
		categoryService.createAndUpdateCategory(dto);
		return ResponseEntity.created(URI.create("/v1/categoty")).build();
	}
	

	@GetMapping("v1/category")
	public ResponseEntity<ResultPageResponDTO<CategoryListResponseDTO>> findCategoryList(
			@RequestParam(name="pages",required = true,defaultValue = "0") Integer pages,
			@RequestParam(name = "limit", required = true, defaultValue = "10") Integer limit,
			@RequestParam(name= "soryBy", required = true,defaultValue = "name") String sortBy,
			@RequestParam(name="direction",required = true,defaultValue = "asc") String direction,
			@RequestParam(name = "categoryName",required = false) String categoryName){
		
		return ResponseEntity.ok().body(categoryService.findCategoryList(pages, limit, sortBy, direction, categoryName));
	}
}
