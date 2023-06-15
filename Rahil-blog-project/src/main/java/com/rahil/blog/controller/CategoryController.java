package com.rahil.blog.controller;

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

import com.rahil.blog.payload.ApiResponse;
import com.rahil.blog.payload.CategoryDto;
import com.rahil.blog.service.CategoryService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {

	@Autowired
	CategoryService categoryService;
	
	//create
	
	@PostMapping("/create-category")
	public ResponseEntity<CategoryDto> createCategory(@Valid @RequestBody CategoryDto categoryDto){
		System.out.println(categoryDto);
		CategoryDto createCategory=this.categoryService.createCategory(categoryDto);
		
		return new ResponseEntity<CategoryDto>(createCategory,HttpStatus.CREATED);
		
	}
	
	//update
	@PutMapping("/update-category/{id}")
	public ResponseEntity<CategoryDto> updateCategory(@Valid @RequestBody CategoryDto categoryDto,@PathVariable("id") Integer id){
		CategoryDto updateCategory=this.categoryService.updateCategory(categoryDto, id);
		return new  ResponseEntity<CategoryDto>(updateCategory,HttpStatus.OK);
	}
	
	
	//delete
	
	@DeleteMapping("/delete-category/{id}")
	public ResponseEntity<ApiResponse> deleteCategory(@PathVariable("id") Integer id){
		this.categoryService.deleteCategory(id);
		return new ResponseEntity<ApiResponse>(new ApiResponse("category is deleted successfully",true),HttpStatus.OK);
	}
	
	//get
	@GetMapping("/get-singlecate/{id}")
	public ResponseEntity<CategoryDto> getCategory(@PathVariable Integer id){
		
		CategoryDto categoryDto=this.categoryService.getCategory(id);
		
		return new ResponseEntity<CategoryDto>(categoryDto,HttpStatus.OK);
		
	}
	
	
	//getAll
	@GetMapping("/get-allCat")
	public ResponseEntity<List<CategoryDto>> getAllCategories(){
		List<CategoryDto> categoryDtos=this.categoryService.getAllCategories();
		return ResponseEntity.ok(categoryDtos);
	}
}
