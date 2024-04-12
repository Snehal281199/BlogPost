package com.example.demo.Controller;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.DTO.ApiResponse;
import com.example.demo.DTO.UserDto;
import com.example.demo.Entity.User;
import com.example.demo.Service.UserService;



import org.springframework.web.bind.annotation.PathVariable;


@RestController
@RequestMapping("/api/v1/users")
public class userController {
	@Autowired
	private UserService userService;
	
	//post --create user
	@PostMapping("/")
	public ResponseEntity<UserDto> createUser(@Validated @RequestBody UserDto userDto){
		UserDto createUserDto=this.userService.createUser(userDto);
		return new ResponseEntity<>(createUserDto ,HttpStatus.CREATED);
	}
	//put ---update user
	@PutMapping("/{userId}")
	public ResponseEntity<UserDto> update(@Validated @RequestBody  UserDto userDto, @PathVariable  Integer userId){
		UserDto updateUser=this.userService.update(userDto, userId);
		return  ResponseEntity.ok(updateUser);
	}
	
	//get---user get
	@GetMapping("/")
	public ResponseEntity<List<UserDto>>getAllUsers(){
		return ResponseEntity.ok(this.userService.getAllUsers());
	}
	
	@GetMapping("/{userId}")
	public ResponseEntity<UserDto> getUserById(@PathVariable Integer userId) {
		return ResponseEntity.ok(this.userService.getUserById(userId));
	}
	//admin
	//delete --remove
	@PreAuthorize("hasRole('ADMIN')")
	@DeleteMapping("/{userId}")
	public ResponseEntity<ApiResponse> deleteUser(@PathVariable Integer userId){
		
		this.userService.deleteUser(userId);
		return new ResponseEntity<ApiResponse>(new ApiResponse("USer deleted Successfully",true),HttpStatus.OK);
		
	}
	
	
	
	
}
