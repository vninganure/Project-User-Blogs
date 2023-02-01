package com.user.blogApis.services.Impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.user.blogApis.entities.Category;
import com.user.blogApis.exceptions.ResourseNotFoundException;
import com.user.blogApis.payloads.CategoryDto;
import com.user.blogApis.repositories.CategoryRepo;
import com.user.blogApis.services.CategoryService;


@Service
public class CategoryServiceImpl implements CategoryService{
	
	@Autowired
	private CategoryRepo categoryRepo;
	
	@Autowired
	private ModelMapper modelMapper;
	
	
	@Override
	public CategoryDto createCategory(CategoryDto categoryDto) {
		
		Category category  = this.modelMapper.map(categoryDto, Category.class);
		Category addedCat = this.categoryRepo.save(category);
		
		return this.modelMapper.map(addedCat, CategoryDto.class);
	}

	@Override
	public CategoryDto updateCategory(CategoryDto categoryDto, Integer categoryId) {
		Category category = this.categoryRepo.findById(categoryId)
				.orElseThrow(()-> new ResourseNotFoundException("Category", "Id", categoryId));
				
		category.setCategoryName(categoryDto.getCategoryName());
		category.setCategoryDescription(categoryDto.getCategoryDescription());
		
		Category updatedCat = this.categoryRepo.save(category);
		
		return this.modelMapper.map(updatedCat, CategoryDto.class);
	}

	@Override
	public void deleteCategory(Integer categoryId) {
		
		Category category = this.categoryRepo.findById(categoryId)
				.orElseThrow(()->new ResourseNotFoundException("Category", "Category Id", categoryId));
				
		this.categoryRepo.delete(category);
		
	}

	@Override
	public List<CategoryDto> getAllCategory() {
		List<Category> categories = this.categoryRepo.findAll();
		
		List<CategoryDto> dtos = categories.stream().map((cat)->this.modelMapper.map(cat, CategoryDto.class)).collect(Collectors.toList());
		
		
		return dtos;
	}

	@Override
	public CategoryDto getCategory(Integer categoryId) {
		Category category = this.categoryRepo.findById(categoryId)
				.orElseThrow(()->new ResourseNotFoundException("Category" , "Category Id", categoryId));
		
		return this.modelMapper.map(category, CategoryDto.class);
	}

}
