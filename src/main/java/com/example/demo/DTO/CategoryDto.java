package com.example.demo.DTO;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CategoryDto {

	
	
	private Integer categoryId;
	
	@NotBlank
	@Size(min=5,message="Min size of category is 5")
	private String categoryTitle;
	@NotBlank
	@Size(min=10,message="Min size of category is 10")
	private String categoryDescription;
	
}
