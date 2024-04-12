package com.example.demo.Service;

import com.example.demo.DTO.CommentDto;

public interface CommentService {
	
      CommentDto createComment(CommentDto commentDto,Integer postId);
      
      void deleteComment(Integer commentId);
      

}
