package com.ucorp.catalog.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.ucorp.catalog.domain.Category;
import com.ucorp.catalog.dto.CategoryCreateUpdateRecordDTO;
import com.ucorp.catalog.dto.CategoryListResponseDTO;
import com.ucorp.catalog.dto.CategoryQueryDTO;
import com.ucorp.catalog.dto.CreateUpdateRequestCategoryDTO;
import com.ucorp.catalog.dto.ResultPageResponDTO;
import com.ucorp.catalog.exception.BadRequestException;
import com.ucorp.catalog.repository.CategoryRepository;
import com.ucorp.catalog.service.CategoryService;
import com.ucorp.catalog.util.PaginationUtil;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class CategoryServiceImpl implements CategoryService {

	private final CategoryRepository categoryRepository;

	@Override
	public void createAndUpdateCategory(CategoryCreateUpdateRecordDTO dto) {
		/**
		 * buat kalau code sudah ada refactor kalao belum bikin
		 */
		
		Category category = categoryRepository.findByCode(dto.code().toLowerCase()).orElse(new Category());
		if (category.getCode() == null) {
			category.setCode(dto.code().toLowerCase());// jika category Null atau membuat
		}
		category.setName(dto.name());
		category.setDescription(dto.description());

		categoryRepository.save(category);
	}

	@Override
	public ResultPageResponDTO<CategoryListResponseDTO> findCategoryList(Integer pages, Integer limit, String sortBy,
			String direction, String categoryName) {
		categoryName = StringUtils.isEmpty(categoryName) ? "%" : categoryName + "%";

		Sort sort = Sort.by(new Sort.Order(PaginationUtil.getSortBy(direction), sortBy));
		Pageable pageable = PageRequest.of(pages, limit, sort);

		Page<Category> pageResult = categoryRepository.findByNameLikeIgnoreCase(categoryName, pageable);

		List<CategoryListResponseDTO> dto = pageResult.stream().map((c) -> {
			CategoryListResponseDTO dtos = new CategoryListResponseDTO();
			dtos.setCode(c.getCode());
			dtos.setName(c.getName());
			dtos.setDescription(c.getDescription());
			return dtos;
		}).collect(Collectors.toList());
		// TODO Auto-generated method stub
		return PaginationUtil.createResultPageDTO(dto, pageResult.getTotalElements(), pageResult.getTotalPages());
	}

	@Override
	public List<Category> findCategories(List<String> categoryCodeList) {
		List<Category> categories = categoryRepository.findByCodeIn(categoryCodeList);
		if (categories.isEmpty())
			throw new BadRequestException("Category Cant Empty");
		return categories;
	}

	@Override
	public List<CategoryListResponseDTO> constructDTO(List<Category> categories) {
		return categories.stream().map((c) -> {
			CategoryListResponseDTO dto = new CategoryListResponseDTO();
			dto.setCode(c.getCode());
			dto.setName(c.getName());
			dto.setDescription(c.getDescription());
			return dto;
		}).collect(Collectors.toList());
	}

	@Override
	public Map<Long, List<String>> findCategoriesMap(List<Long> bookIdList) {
		List<CategoryQueryDTO> queryList = categoryRepository.findCategoryByBookIdList(bookIdList);
		Map<Long, List<String>> categoryMaps = new HashMap<>();
		List<String> categoryCodeList = null;
		for (CategoryQueryDTO q : queryList) {
			if (!categoryMaps.containsKey(q.getBookId())) {
				categoryCodeList = new ArrayList<>();
			} else {
				categoryCodeList = categoryMaps.get(q.getBookId());
			}
			categoryCodeList.add(q.getCategoryCode());
			categoryMaps.put(q.getBookId(), categoryCodeList);
		}
		return categoryMaps;
	}

}
