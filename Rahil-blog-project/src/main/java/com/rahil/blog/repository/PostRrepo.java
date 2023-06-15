package com.rahil.blog.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.rahil.blog.entity.Category;
import com.rahil.blog.entity.Post;
import com.rahil.blog.entity.User;
import com.rahil.blog.payload.PostDto;

public interface PostRrepo extends JpaRepository<Post, Integer>{

	List<Post> findByUser(User user);
	
	List<Post> findByCategory(Category category);
	
	@Query("select p from Post p where p.title like:key")
	List<Post> findByTitleContaining(@Param("key") String title);
	
}
