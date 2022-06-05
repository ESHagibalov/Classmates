package com.classmates.demo.repository;

import com.classmates.demo.models.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PostsRepository extends JpaRepository<Post, Long> {
    Optional<Post> findById(Long id);
    Optional<Post> findByContent(String content);
}
