package com.example.demo.DTO;

import lombok.Data;

@Data
public class JwtAuthRequest {

	
	private String username; //email
	private String password;
}
