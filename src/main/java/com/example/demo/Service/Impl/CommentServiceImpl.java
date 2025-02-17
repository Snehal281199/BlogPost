package com.example.demo.Service.Impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.DTO.CommentDto;
import com.example.demo.Entity.Comment;
import com.example.demo.Entity.Post;
import com.example.demo.Exception.ResourceNotFoundException;
import com.example.demo.Repository.CommentRepo;
import com.example.demo.Repository.PostRepo;
import com.example.demo.Service.CommentService;

@Service
public class CommentServiceImpl  implements CommentService{

	@Autowired
	private PostRepo postRepo;
	@Autowired
	private CommentRepo commentRepo;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Override
	public CommentDto createComment(CommentDto commentDto, Integer postId) {
Post post=	this.postRepo.findById(postId).orElseThrow(()-> new ResourceNotFoundException("Post","post_id",postId));
Comment comment=this.modelMapper.map(commentDto,Comment.class);	
comment.setPost(post);

Comment savedComment=commentRepo.save(comment);


		return this.modelMapper.map(savedComment,CommentDto.class);
	}

	@Override
	public void deleteComment(Integer commentId) {
		Comment com=this.commentRepo.findById(commentId).orElseThrow(()-> new ResourceNotFoundException("Comment","commentId",commentId));
		this.commentRepo.delete(com);
		
	}

}
