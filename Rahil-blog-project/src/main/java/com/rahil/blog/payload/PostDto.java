package com.rahil.blog.payload;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import com.rahil.blog.entity.Category;
import com.rahil.blog.entity.Comment;
import com.rahil.blog.entity.User;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PostDto {

	private int id;
	@NotEmpty
	@Size(min=5)
	private String title;
	@NotEmpty
	private String content;
	
	private String imageName;
	
	private Date AddDate;
	
	private CategoryDto category;
	
	private UserDto user;
	
	private Set<CommentDto> comments=new HashSet<>();
	
}
