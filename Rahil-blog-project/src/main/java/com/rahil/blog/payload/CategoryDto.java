package com.rahil.blog.payload;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CategoryDto { 

	private int id;
	@NotBlank
	@Size(min=4,message = "must enter atleat 4 charecter")
	private String title;
	@NotBlank
	@Size(min=10,message = "must enter atleast 10 charecter")
	private String categoryDescription;
}
