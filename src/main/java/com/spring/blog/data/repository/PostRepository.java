package com.spring.blog.data.repository;

import com.spring.blog.data.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Integer> {

}
