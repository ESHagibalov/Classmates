package com.classmates.demo.models;

import lombok.Data;

@Data
public class PostModel {
    Long id;

    String content;

    Long userId;

    String path;

    public PostModel(Long id, String content, Long userId, String path) {
        this.id = id;
        this.content = content;
        this.userId = userId;
        this.path = path;
    }
}
