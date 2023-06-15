package com.rahil.blog.exceptions;
	
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.rahil.blog.payload.ApiResponse;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<ApiResponse> resourceNotFoundExceptionHandler(ResourceNotFoundException exe){
		String messege=exe.getMessage();
		ApiResponse apiResponse=new ApiResponse(messege,false);
		return new ResponseEntity<ApiResponse>(apiResponse,HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Map<String, String>> handleMethodArgumentNotValidException(MethodArgumentNotValidException e){
		
		Map<String,String> map=new HashMap<>();
		e.getBindingResult().getAllErrors().forEach((error)->{
			String fieldname=((FieldError)error).getField();
			String messege=error.getDefaultMessage();
			map.put(fieldname, messege);
		});
		
		return new ResponseEntity<Map<String,String>>(map,HttpStatus.BAD_REQUEST);
		
	}
}
