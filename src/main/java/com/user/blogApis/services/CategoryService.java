package com.user.blogApis.services;

import java.util.List;

import com.user.blogApis.payloads.CategoryDto;

public interface CategoryService {

	//create
	CategoryDto createCategory(CategoryDto categoryDto);
	
	//Update
	CategoryDto updateCategory(CategoryDto categoryDto, Integer categoryId);
	
	//Delete
	void deleteCategory(Integer categoryId);
	
	//GetAll
	List<CategoryDto> getAllCategory();
	
	//get single category
	CategoryDto getCategory(Integer categoryId);
}
