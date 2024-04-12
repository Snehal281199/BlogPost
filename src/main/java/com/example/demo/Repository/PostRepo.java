package com.example.demo.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.Entity.Category;
import com.example.demo.Entity.Post;
import com.example.demo.Entity.User;

public interface PostRepo extends JpaRepository<Post, Integer> {
	
List<Post> findByUser(User user);
List<Post> findByCategory(Category category);
}
