package com.example.demo.Service;

import java.util.List;

import com.example.demo.DTO.PostDto;


public interface PostService {

	//create
PostDto createPost(PostDto postDto, Integer userId, Integer categoryId);
	
	//update
PostDto updatePost(PostDto postDto, Integer postId);
	
	//delete
	void deletePost(Integer postId);
	
	//get all
	List<PostDto> getAllPost();

	
	PostDto getPostById(Integer postId);
	
	//get all post by user;
	List<PostDto> getPostByUser(Integer userId);
	
	//get all post by category;
	List<PostDto> getPostByCategory(Integer categoryId);
	
	
}
