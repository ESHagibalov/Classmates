package com.classmates.demo.models;

import lombok.Data;

@Data
public class PostModel {
    Long id;

    String content;

    Long userId;

    public PostModel(Long id, String content, Long userId) {
        this.id = id;
        this.content = content;
        this.userId = userId;
    }
}
