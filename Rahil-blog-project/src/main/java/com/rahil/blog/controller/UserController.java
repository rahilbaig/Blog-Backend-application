package com.rahil.blog.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rahil.blog.payload.ApiResponse;
import com.rahil.blog.payload.UserDto;
import com.rahil.blog.service.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/users/")
public class UserController {

   @Autowired
   private UserService service;
   
   @PostMapping("/add-user")
   public ResponseEntity<UserDto> createUser(@Valid @RequestBody UserDto userDto){
	   
	   UserDto createUserDto=this.service.createUser(userDto);
	   
	   return new ResponseEntity<>(createUserDto,HttpStatus.CREATED);
	   
   }
   @PutMapping("/update-user/{id}")
   public ResponseEntity<UserDto>updateUser(@Valid @RequestBody UserDto userDto,@PathVariable("id") Integer id){
	   System.out.println(id);
	   System.out.println(userDto.getEmail());
	   UserDto upadteUserDto=this.service.updateUser(userDto, id);
	   return ResponseEntity.ok(upadteUserDto);
   }
   
   @DeleteMapping("/delete-user/{id}")
   public ResponseEntity<ApiResponse> deleteUser(@PathVariable("id") Integer id){
	   
	   this.service.deleteUser(id);
	   return new ResponseEntity<ApiResponse>(new ApiResponse("user deleted successfull",true),HttpStatus.OK);
	   
   }
   
   @GetMapping("/getall-users")
   public ResponseEntity<List<UserDto>> getAllUsers(){
	   return ResponseEntity.ok(this.service.getAllUser());
	   
   }
   
   @GetMapping("/getsingle/{id}")
   public ResponseEntity<UserDto> getSingleUser(@PathVariable Integer id){
	   
	   return ResponseEntity.ok(service.getUserById(id));
   }
   
}
