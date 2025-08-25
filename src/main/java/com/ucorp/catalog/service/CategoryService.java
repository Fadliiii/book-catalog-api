package com.ucorp.catalog.service;

import java.util.List;
import java.util.Map;

import com.ucorp.catalog.domain.Category;
import com.ucorp.catalog.dto.CategoryCreateUpdateRecordDTO;
import com.ucorp.catalog.dto.CategoryListResponseDTO;
import com.ucorp.catalog.dto.CreateUpdateRequestCategoryDTO;
import com.ucorp.catalog.dto.ResultPageResponDTO;

public interface CategoryService {

	public void createAndUpdateCategory(CategoryCreateUpdateRecordDTO dto);

	public ResultPageResponDTO<CategoryListResponseDTO> findCategoryList(Integer pages, Integer limit, String sortBy,
			String direction, String categoryName);

	public List<Category> findCategories(List<String> categoryCodeList);
	
	public List<CategoryListResponseDTO> constructDTO(List<Category>categories);

	public Map<Long, List<String>> findCategoriesMap(List<Long>bookIdList);
}
