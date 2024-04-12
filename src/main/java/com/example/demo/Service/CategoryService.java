package com.example.demo.Service;

import java.util.List;

import com.example.demo.DTO.CategoryDto;



public interface CategoryService {

	
	//create
	public CategoryDto createCategory(CategoryDto categoryDto);
	
	//update
	public CategoryDto updateCategory(CategoryDto categoryDto, Integer categoryId);
	
	//delete
	public void deleteCategory(Integer categoryId);
	
	//get
	public CategoryDto getCategory(Integer categoryId);
	//getAall

	List<CategoryDto> getCategories();
}
