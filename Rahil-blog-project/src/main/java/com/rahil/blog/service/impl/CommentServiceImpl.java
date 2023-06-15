package com.rahil.blog.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rahil.blog.entity.Comment;
import com.rahil.blog.entity.Post;
import com.rahil.blog.exceptions.ResourceNotFoundException;
import com.rahil.blog.payload.CommentDto;
import com.rahil.blog.repository.CommentRepo;
import com.rahil.blog.repository.PostRrepo;
import com.rahil.blog.service.CommentService;

@Service
public class CommentServiceImpl implements CommentService {

	@Autowired
	PostRrepo postRrepo;
	
	@Autowired
	CommentRepo commentRepo;
	
	@Autowired
	private ModelMapper mapper;
	
	@Override
	public CommentDto createComment(CommentDto commentDto, Integer postId) {
		
		System.out.println(commentDto);
		
		Post post=this.postRrepo.findById(postId).orElseThrow(()->new ResourceNotFoundException("post", "post id", postId));
		
		Comment comment=this.mapper.map(commentDto, Comment.class);
		
		comment.setPost(post);
		
		Comment saveCommment= this.commentRepo.save(comment);
		
		return this.mapper.map(saveCommment, CommentDto.class);
	}

	
	@Override
	public void deleteComment(Integer commentId) {
		
		
		Comment comment=this.commentRepo.findById(commentId).orElseThrow(()-> new ResourceNotFoundException("Comment", "comment id", commentId));
		
		this.commentRepo.delete(comment);
		
		
	}

}
