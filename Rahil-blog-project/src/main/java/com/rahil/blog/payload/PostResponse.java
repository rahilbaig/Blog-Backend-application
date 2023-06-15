package com.rahil.blog.payload;

import java.util.List;

import com.rahil.blog.entity.Post;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class PostResponse {

	private List<PostDto> content;
	private int pagenumber;
	private int pageSize;
	private long totalElements;
	private int totalPages;
	private boolean lastPages;
	
}
