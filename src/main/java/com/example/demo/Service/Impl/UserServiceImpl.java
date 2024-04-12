package com.example.demo.Service.Impl;

import java.util.List;
import java.util.stream.Collectors;


import javax.security.auth.login.AppConfigurationEntry;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.DTO.UserDto;
import com.example.demo.Entity.Role;
import com.example.demo.Entity.User;
import com.example.demo.Exception.ResourceNotFoundException;
import com.example.demo.Repository.RoleRepo;
import com.example.demo.Repository.UserRepo;
import com.example.demo.Service.UserService;
import com.example.demo.config.AppConstants;
@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepo userRepo;
	
	@Autowired
	private ModelMapper modelMapper;
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private RoleRepo roleRepo;
	
	@Override
	public UserDto createUser(UserDto userDto) {
		User user=dtoToUser(userDto);
		User savedUser=this.userRepo.save(user);
		return this.UserToDto(savedUser);
	}

	@Override
	public UserDto update(UserDto userDto, Integer userId) {
		User user =this.userRepo.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User","id" ,userId));
		
		user.setName(userDto.getName());
		user.setEmail(userDto.getEmail());
		user.setPassword(userDto.getPassword());
		user.setAbout(userDto.getAbout());
		
		User updatedUser=this.userRepo.save(user);
		
		UserDto userDto1=this.UserToDto(updatedUser);
		return userDto1;
	}

	@Override
	public UserDto getUserById(Integer userId) {
		User user=this.userRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User", "Id",userId));
		return this.UserToDto(user);
	}

	@Override
	public List<UserDto> getAllUsers() {
		List<User> users=this.userRepo.findAll();
		
	List<UserDto> userDtos=	users.stream().map(user->this.UserToDto(user)).collect(Collectors.toList());
	
		return userDtos;
	}

	@Override
	public void deleteUser(Integer userId) {
	User user=	userRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User","Id",userId));
		this.userRepo.delete(user);
	}
public User dtoToUser(UserDto userDto) {
	
	User user =this.modelMapper.map(userDto, User.class);
	/*
	 * user.setId(userDto.getId()); user.setName(userDto.getName());
	 * user.setEmail(userDto.getEmail()); user.setPassword(user.getPassword());
	 * user.setAbout(userDto.getAbout());
	 */
return user;

}

public UserDto UserToDto(User user) {
	UserDto userDto=this.modelMapper.map(user, UserDto.class);
	/*
	 * userDto.setId(user.getId()); userDto.setName(user.getName());
	 * userDto.setEmail(user.getEmail()); userDto.setPassword(user.getPassword());
	 * userDto.setAbout(user.getAbout());
	 */
	
	return userDto;
	
	
}

@Override
public UserDto registerNewUser(UserDto userDto) {
	User user=this.modelMapper.map(userDto,User.class);
	user.setPassword(this.passwordEncoder.encode(user.getPassword()));//encoded the regostered password
	//how new roles to be assigned to new users
Role role=	this.roleRepo.findById(AppConstants.NORMAL_USER).get();
	user.getRoles().add(role);
	User newUser=this.userRepo.save(user);
return this.modelMapper.map(newUser, UserDto.class);
}
	
	
}

