package com.example.demo.DTO;

import lombok.Data;

@Data
public class JwtAuthResponse {

	
	private String token;
	private UserDto user;
}
