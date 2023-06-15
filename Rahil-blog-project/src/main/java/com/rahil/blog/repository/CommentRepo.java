package com.rahil.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rahil.blog.entity.Comment;

public interface CommentRepo extends JpaRepository<Comment, Integer> {

}
