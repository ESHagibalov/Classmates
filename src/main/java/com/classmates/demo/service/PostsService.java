package com.classmates.demo.service;

import com.classmates.demo.models.Post;
import com.classmates.demo.models.User;
import com.classmates.demo.repository.PostsRepository;
import com.classmates.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostsService {

    private final UserRepository userRepository;
    private final PostsRepository postsRepository;

    @Autowired
    public PostsService(UserRepository userRepository, PostsRepository postsRepository) {
        this.userRepository = userRepository;
        this.postsRepository = postsRepository;
    }


    public Post create(Post post, User user) {
        post.setUser(user);
        return postsRepository.save(post);
    }
}
