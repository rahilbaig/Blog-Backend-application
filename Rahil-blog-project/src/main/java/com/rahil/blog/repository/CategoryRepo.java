package com.rahil.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rahil.blog.entity.Category;

public interface CategoryRepo extends JpaRepository<Category, Integer> {

}
