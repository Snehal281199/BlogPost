package com.example.demo.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.Entity.Comment;


public interface CommentRepo extends JpaRepository<Comment, Integer> {

}
