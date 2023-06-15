package com.rahil.blog.service;

import java.util.List;

import com.rahil.blog.payload.CategoryDto;

public interface CategoryService {

	//create

	CategoryDto createCategory(CategoryDto categoryDto);

	//update

	CategoryDto updateCategory(CategoryDto categoryDto,Integer id);

	//delete

	void deleteCategory(Integer id);
	
	//get

	CategoryDto getCategory(Integer id);
	
	//getAll
	
	List<CategoryDto> getAllCategories();
}
