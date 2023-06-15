package com.rahil.blog.service;

import java.util.List;

import com.rahil.blog.entity.Post;
import com.rahil.blog.entity.User;
import com.rahil.blog.payload.PostDto;
import com.rahil.blog.payload.PostResponse;
import com.rahil.blog.payload.UserDto;

public interface Postservice {

	//create
	
	PostDto createPost(PostDto dto,Integer userId,Integer categoryId);
	
	//update 
	
	PostDto updatePost(PostDto dto,Integer id);
	
	//delete
	
	void deletePost(Integer id);
	
	//get post
	
	PostDto getPostById(Integer id);
	
	//get All Post
	
	PostResponse getAllPost(Integer pageNumber,Integer pageSize,String sortBy ,String sortDir);
	
	//get All Post by Category
	
	List<PostDto> getPostByCategory(Integer categoryId);
	
	
	//get All Post by userName
	
	List<PostDto> getPostByUser(Integer userId);
	
	//search post
	List<PostDto> SearchPosts(String keyword);
	
	
}
//7411341628 arfat
