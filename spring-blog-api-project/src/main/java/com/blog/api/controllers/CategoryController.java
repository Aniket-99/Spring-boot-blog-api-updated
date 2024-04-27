package com.blog.api.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.blog.api.payloads.ApiResponse;
import com.blog.api.payloads.CategoryDTO;
import com.blog.api.services.CategoryService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/category")
public class CategoryController {

	@Autowired
	private CategoryService categoryService;

	@PostMapping("/")
	public ResponseEntity<CategoryDTO> createCategory(@Valid @RequestBody CategoryDTO categoryDTO) {
		CategoryDTO category = this.categoryService.createCategory(categoryDTO);

		return new ResponseEntity<CategoryDTO>(category, HttpStatus.CREATED);
	}

	@PutMapping("/{id}")
	public ResponseEntity<CategoryDTO> updateCategory(@Valid @RequestBody CategoryDTO categoryDTO,
			@PathVariable("id") Integer id) {
		CategoryDTO updateCategory = this.categoryService.updateCategory(categoryDTO, id);
		return new ResponseEntity<CategoryDTO>(updateCategory, HttpStatus.CREATED);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<ApiResponse> deleteMapping(@PathVariable("id") Integer id) {
		this.categoryService.deleteCategory(id);
		return new ResponseEntity<ApiResponse>(new ApiResponse("Deleted successfully", true), HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<CategoryDTO> getSingleCategory(@PathVariable("id") Integer id) {
		return ResponseEntity.ok(this.categoryService.getSingleCategory(id));
	}

	@GetMapping("/")
	public ResponseEntity<List<CategoryDTO >> getAllCategories() {
		return ResponseEntity.ok(this.categoryService.getAllCategories());
	}

}
