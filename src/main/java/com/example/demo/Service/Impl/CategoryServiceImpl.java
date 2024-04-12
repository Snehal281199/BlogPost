package com.example.demo.Service.Impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.DTO.CategoryDto;
import com.example.demo.Entity.Category;
import com.example.demo.Exception.ResourceNotFoundException;
import com.example.demo.Repository.CategoryRepo;
import com.example.demo.Service.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService {

	@Autowired
	private CategoryRepo categoryRepo;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Override
	public CategoryDto createCategory(CategoryDto categoryDto) {
		 Category cat=this.modelMapper.map(categoryDto, Category.class);
		Category adddedcat=this.categoryRepo.save(cat);
		return this.modelMapper.map(adddedcat, CategoryDto.class);
	}

	@Override
	public CategoryDto updateCategory(CategoryDto categoryDto, Integer categoryId) {
		Category cat=	this.categoryRepo.findById(categoryId).orElseThrow(()-> new ResourceNotFoundException("Category","Category ID",categoryId));
	cat.setCategoryTitle(categoryDto.getCategoryTitle());
	cat.setCategoryDescription(categoryDto.getCategoryDescription());
	
	Category updatecat=this.categoryRepo.save(cat);
	return this.modelMapper.map(updatecat, CategoryDto.class);
	
	
	
	}

	@Override
	public void deleteCategory(Integer categoryId) {
		Category cat=this.categoryRepo.findById(categoryId).orElseThrow(()-> new ResourceNotFoundException("Category","Category ID",categoryId));
		this.categoryRepo.delete(cat);
	}

	@Override
	public CategoryDto getCategory(Integer categoryId) {
		Category cat=this.categoryRepo.findById(categoryId).orElseThrow(()-> new ResourceNotFoundException("Category","Category ID",categoryId));
		return this.modelMapper.map(cat, CategoryDto.class);
	}

	@Override
	public List<CategoryDto> getCategories() {
		
		List<Category> categories=this.categoryRepo.findAll();
		List<CategoryDto> catDtos=categories.stream().map((cat)->this.modelMapper.map(cat, CategoryDto.class)).collect(Collectors.toList());		
		return 	catDtos;
		
		
		
	
	}

	
	

}
