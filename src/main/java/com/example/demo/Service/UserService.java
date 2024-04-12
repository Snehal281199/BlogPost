package com.example.demo.Service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.DTO.UserDto;
import com.example.demo.Entity.User;

@Service
public interface UserService {

	UserDto registerNewUser(UserDto userDto);
	UserDto createUser(UserDto user);
	UserDto update(UserDto user,Integer userId);
	UserDto getUserById(Integer userId);
	List<UserDto>getAllUsers();
	void deleteUser(Integer userId);
	
	
	
}
