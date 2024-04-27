package com.blog.api.payloads;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public class CategoryDTO {

	private Integer categoryId;

	@NotEmpty(message = "Category name cannot be empty")
	@Size(min = 4,message = "Category should be minimum 4 characters")
	private String categoryName;

	@NotEmpty(message = "Category description cannot be empty")
	private String categoryDescription;

	public CategoryDTO() {
		super();
	}

	public CategoryDTO(Integer categoryId, String categoryName, String categoryDescription) {
		super();
		this.categoryId = categoryId;
		this.categoryName = categoryName;
		this.categoryDescription = categoryDescription;
	}

	public Integer getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public String getCategoryDescription() {
		return categoryDescription;
	}

	public void setCategoryDescription(String categoryDescription) {
		this.categoryDescription = categoryDescription;
	}

}
