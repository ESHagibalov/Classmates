package com.classmates.demo.payload.response;

import com.classmates.demo.models.Post;
import com.classmates.demo.models.PostModel;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Data
public class GetUserPosts {
    Set<PostModel> posts = new HashSet<>();

    public GetUserPosts(Set<PostModel> posts1) {
        System.out.println("Here ok");
        posts.addAll(posts1);
        System.out.println("Here ok too");
    }

    public Set<PostModel> getPosts() {
        return posts;
    }

    public void setPosts(Set<PostModel> posts) {
        this.posts = posts;
    }
}
