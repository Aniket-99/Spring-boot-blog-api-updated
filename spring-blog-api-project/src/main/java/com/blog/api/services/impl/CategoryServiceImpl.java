package com.blog.api.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blog.api.entities.Category;
import com.blog.api.exceptions.ResourceNotFoundException;
import com.blog.api.payloads.CategoryDTO;
import com.blog.api.repositories.CategoryRepo;
import com.blog.api.services.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService {

	@Autowired
	private CategoryRepo categoryRepo;

	@Autowired
	private ModelMapper modelMapper;

	@Override
	public CategoryDTO createCategory(CategoryDTO categoryDTO) {
		Category category = this.modelMapper.map(categoryDTO, Category.class);
		Category savedCategory = this.categoryRepo.save(category);
		return this.modelMapper.map(savedCategory, CategoryDTO.class);
	}

	@Override
	public CategoryDTO updateCategory(CategoryDTO categoryDTO, Integer id) {

		Category category = this.categoryRepo.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Category", "Id", id));
		category.setCategoryName(categoryDTO.getCategoryName());
		category.setCategoryDescription(categoryDTO.getCategoryDescription());

		Category saveCategory = this.categoryRepo.save(category);

		return this.modelMapper.map(saveCategory, CategoryDTO.class);
	}

	@Override
	public void deleteCategory(Integer id) {
		Category category = this.categoryRepo.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Category", "Id", id));

		this.categoryRepo.delete(category);

	}

	@Override
	public CategoryDTO getSingleCategory(Integer id) {
		Category category = this.categoryRepo.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Category", "Id", id));
		return this.modelMapper.map(category, CategoryDTO.class);
	}

	@Override
	public List<CategoryDTO> getAllCategories() {
		List<Category> allCategories = this.categoryRepo.findAll();
		return allCategories.stream().map((category) -> this.modelMapper.map(category, CategoryDTO.class))
				.collect(Collectors.toList());
	}

}
