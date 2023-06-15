package com.rahil.blog.service;

import java.util.List;

import com.rahil.blog.payload.UserDto;

public interface UserService {


	UserDto createUser(UserDto user);
	UserDto updateUser(UserDto user,Integer id);
	UserDto getUserById(Integer id);
	List<UserDto> getAllUser();
	void deleteUser(Integer id);
}
