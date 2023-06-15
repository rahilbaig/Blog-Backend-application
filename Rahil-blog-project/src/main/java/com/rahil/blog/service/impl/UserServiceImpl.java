package com.rahil.blog.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rahil.blog.entity.User;
import com.rahil.blog.exceptions.ResourceNotFoundException;
import com.rahil.blog.payload.UserDto;
import com.rahil.blog.repository.UserRepo;
import com.rahil.blog.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepo repo;
	
	@Autowired
	ModelMapper modelMapper;
	
	@Override
	public UserDto createUser(UserDto user) {
		User user2=this.dtoToUser(user);
		User saveUser=this.repo.save(user2);
		return this.userToDto(saveUser);
	}

	@Override
	public UserDto updateUser(UserDto userDto, Integer userId) {
		User user=this.repo.findById(userId)
				.orElseThrow(()-> new ResourceNotFoundException("user","userId", userId));
		user.setName(userDto.getName());
		user.setEmail(userDto.getEmail());
		user.setPassword(userDto.getPassword());
		user.setAbout(userDto.getAbout());
		User updatedUser= repo.save(user);
		return this.userToDto(updatedUser);
	}

	@Override
	public UserDto getUserById(Integer id) {
	   User user=repo.findById(id).orElseThrow(()->new ResourceNotFoundException("user","id",id));
		return this.userToDto(user);
	}

	@Override
	public List<UserDto> getAllUser() {
		List<User> users=this.repo.findAll();
		List<UserDto> dtos=users.stream().map(user->this.userToDto(user)).collect(Collectors.toList());
		return dtos;
	}

	@Override
	public void deleteUser(Integer id) {
		
		User user=this.repo.findById(id).orElseThrow(()->new ResourceNotFoundException("user", "id", id));
		repo.delete(user);
	}
	
	public User dtoToUser(UserDto dto) {
		
		User user=this.modelMapper.map(dto, User.class);
		
//		User user=new User();
//		user.setId(dto.getId());
//		user.setName(dto.getName());
//		user.setEmail(dto.getEmail());
//		user.setPassword(dto.getPassword());
//		user.setAbout(dto.getAbout());
		return user;
	}
	
	public UserDto userToDto(User user) {
		
		UserDto dto=this.modelMapper.map(user, UserDto.class);
		
//		UserDto dto=new UserDto();
//		dto.setId(user.getId());
//		dto.setName(user.getName());
//		dto.setEmail(user.getEmail());
//		dto.setPassword(user.getPassword());
//		dto.setAbout(user.getAbout());
		return dto;
	}

}
