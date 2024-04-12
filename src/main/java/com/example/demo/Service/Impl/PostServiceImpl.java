package com.example.demo.Service.Impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.DTO.PostDto;
import com.example.demo.Entity.Category;
import com.example.demo.Entity.Post;
import com.example.demo.Entity.User;
import com.example.demo.Exception.ResourceNotFoundException;
import com.example.demo.Repository.CategoryRepo;
import com.example.demo.Repository.PostRepo;
import com.example.demo.Repository.UserRepo;
import com.example.demo.Service.PostService;

@Service

public class PostServiceImpl implements PostService {

	@Autowired
	private PostRepo postRepo;
		
		@Autowired
		private ModelMapper modelMapper;
		
		@Autowired
		private UserRepo userRepo;
		
		@Autowired
		private CategoryRepo categoryRepo;
@Override
		public List<PostDto> getPostByCategory(Integer categoryId) {	
		Category cat=this.categoryRepo.findById(categoryId)
				.orElseThrow(()-> new ResourceNotFoundException("Category","categoryID",categoryId));	
		List<Post> posts=this.postRepo.findByCategory(cat);
		List<PostDto> postDtos=posts.stream().map((post)->this.modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
		return postDtos;
	}
	@Override
		public List<PostDto> getPostByUser(Integer userId) {
		User user=this.userRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User","userId",userId));
		List<Post>posts=this.postRepo.findByUser(user);	
		List<PostDto> postDtos=posts.stream().map((post)->this.modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
	return postDtos;
		}

	

	@Override
	public PostDto createPost(PostDto postDto, Integer userId, Integer categoryId) {
		User user=this.userRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User ","User id",userId));
		Category category=this.categoryRepo.findById(categoryId).orElseThrow(()->new ResourceNotFoundException("Category","categoryId",categoryId));
		Post post=this.modelMapper.map(postDto, Post.class);
		post.setImageName("default.png");
		post.setUser(user);
		post.setCategory(category);
       Post newPost=this.postRepo.save(post);

		return this.modelMapper.map(newPost, PostDto.class);

	}

	@Override
	public PostDto updatePost(PostDto postDto, Integer postId) {
		Post post=	this.postRepo.findById(postId).orElseThrow(()-> new ResourceNotFoundException("Post","post_id",postId));
		post.setTitle(postDto.getTitle());
		post.setContent(postDto.getContent());
		post.setPostId(postDto.getPostId());
		
	Post updatedPost=this.postRepo.save(post);
	
		return this.modelMapper.map(updatedPost, PostDto.class);
	}

	@Override
	public void deletePost(Integer postId) {
		// TODO Auto-generated method stub
	Post post=	this.postRepo.findById(postId).orElseThrow(()-> new ResourceNotFoundException("Post","post_id",postId));
	this.postRepo.delete(post);
	
	}

	@Override
	public List<PostDto> getAllPost() {

List<Post> allPosts=this.postRepo.findAll();

    List<PostDto> postDtos= allPosts.stream().map((post)->this.modelMapper.map(post,PostDto.class)).collect(Collectors.toList());
		
		
		return postDtos;
	}

	@Override
	public PostDto getPostById(Integer postId) {
	Post post = this.postRepo.findById(postId).orElseThrow(()->new ResourceNotFoundException("Post","postId",postId));
	return this.modelMapper.map(post,PostDto.class);
	}

	

	
	
	/*
	 * private String formatDate(Date date) { SimpleDateFormat sdf=new
	 * SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); return sdf.format(date);
	 * 
	 * }
	 */

}
