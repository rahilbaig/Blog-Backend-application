package com.rahil.blog.payload;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import jakarta.validation.executable.ValidateOnExecution;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class UserDto {

	private int id;
	
	@NotNull
	@Size(min=4,message = "user must be minimum four charecter")
	private String name;
	
	@Email(message = "Email address is not valid")
	@NotEmpty
	private String email;
	
	@NotEmpty
	@Size(min=8,max=16,message = "password must have minimum 8 char and max 8 charecter")
	private String password;
	
	@NotNull
	private String about;
}
