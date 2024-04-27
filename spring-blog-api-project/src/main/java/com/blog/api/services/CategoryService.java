package com.blog.api.services;

import java.util.List;

import com.blog.api.payloads.CategoryDTO;

public interface CategoryService {

	CategoryDTO createCategory(CategoryDTO categoryDTO);
	
	CategoryDTO updateCategory(CategoryDTO categoryDTO, Integer id);
	
	void deleteCategory(Integer id);
	
	CategoryDTO getSingleCategory(Integer id);
	
	List<CategoryDTO> getAllCategories();
	
}
