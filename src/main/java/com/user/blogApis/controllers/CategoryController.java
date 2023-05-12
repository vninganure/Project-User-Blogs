package com.user.blogApis.controllers;

import java.util.List;

import javax.validation.Valid;

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
import org.springframework.web.servlet.function.EntityResponse;

import com.user.blogApis.payloads.ApiResponse;
import com.user.blogApis.payloads.CategoryDto;
import com.user.blogApis.services.CategoryService;

import lombok.val;

@RestController
@RequestMapping("/api/category")
public class CategoryController {

	@Autowired
	private CategoryService categoryService; 
	
	
	//Create
	@PostMapping("/create/")
	public ResponseEntity<CategoryDto> createCategory(@Valid @RequestBody CategoryDto categoryDto) {
		CategoryDto dto = this.categoryService.createCategory(categoryDto);
		
		return new ResponseEntity<CategoryDto>(dto, HttpStatus.CREATED);
	}
	
	//update
	@PutMapping("/update/")
	public ResponseEntity<CategoryDto> updateCategory(@Valid @RequestBody CategoryDto categoryDto, @PathVariable Integer catId){
		CategoryDto updatedDto = this.categoryService.updateCategory(categoryDto, catId);
		
		return ResponseEntity.ok(updatedDto);
	}
	
	//delete
	@DeleteMapping("/delete/{catId}")
	public ResponseEntity<ApiResponse> deleteCategory(@PathVariable Integer catId){
		
		this.categoryService.deleteCategory(catId);
		
		return ResponseEntity.ok(new ApiResponse("Category deleted successfully", true));
	}
	
	@GetMapping("/")
	public ResponseEntity<List<CategoryDto>> getAllCategory(){
		List<CategoryDto> dto = this.categoryService.getAllCategory();
		
		return ResponseEntity.ok(dto);
	}
	
	@GetMapping("/{catId}")
	public ResponseEntity<CategoryDto> getCategory(@PathVariable Integer catId){
		CategoryDto dto = this.categoryService.getCategory(catId);
		
		return ResponseEntity.ok(dto);
	}
	
	
}
