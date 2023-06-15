package com.rahil.blog.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rahil.blog.entity.Category;
import com.rahil.blog.exceptions.ResourceNotFoundException;
import com.rahil.blog.payload.CategoryDto;
import com.rahil.blog.repository.CategoryRepo;
import com.rahil.blog.service.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService {

	@Autowired
	CategoryRepo categoryRepo;

	@Autowired
	ModelMapper mapper;

	@Override
	public CategoryDto createCategory(CategoryDto categoryDto) {

		Category category2= categoryRepo.save(this.mapper.map(categoryDto, Category.class));
		return this.mapper.map(category2, CategoryDto.class);

	}

	@Override
	public CategoryDto updateCategory(CategoryDto categoryDto, Integer id) {
		Category category=this.categoryRepo.findById(id).orElseThrow(()->new ResourceNotFoundException("Category", "Category id", id));

		category.setTitle(categoryDto.getTitle());
		category.setCategoryDescription(categoryDto.getCategoryDescription());

		Category cat=this.categoryRepo.save(category);

		return this.mapper.map(cat, CategoryDto.class);

	}

	@Override
	public void deleteCategory(Integer id) {
		Category category=this.categoryRepo.findById(id).orElseThrow(()->new ResourceNotFoundException("category", "category id", id));
		this.categoryRepo.delete(category);
	}

	@Override
	public CategoryDto getCategory(Integer id) {
		Category category=this.categoryRepo.findById(id).orElseThrow(()->new ResourceNotFoundException("category", "category id", id));
		return this.mapper.map(category, CategoryDto.class);
	}

	@Override
	public List<CategoryDto> getAllCategories() {
		List<Category> categories=this.categoryRepo.findAll();

		List<CategoryDto> categoryDtos=
				categories.stream().map((cat)->
				this.mapper.map(cat, CategoryDto.class)).collect(Collectors.toList());
		return categoryDtos;
	}


	public Category categoryDtoToCategory(CategoryDto categoryDto) {
		Category category=this.mapper.map(categoryDto, Category.class);
		return category;
	}

	public CategoryDto categoryToCategoryDto(Category category) {
		CategoryDto categoryDto=this.mapper.map(category, CategoryDto.class);
		return categoryDto;
	}

}
