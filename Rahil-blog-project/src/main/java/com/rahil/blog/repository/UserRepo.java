package com.rahil.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import com.rahil.blog.entity.User;

public interface UserRepo extends JpaRepository<User, Integer> {

}
