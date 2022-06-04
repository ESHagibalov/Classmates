package com.classmates.demo.payload.response;

import com.classmates.demo.models.User;
import lombok.Data;

import java.util.Optional;


public class SearchResponse {
    Optional<User> user;

    public SearchResponse(Optional<User> user) {
        this.user = user;
    }

    public Optional<User> getUsername() {
        return this.user;
    }

}
//TODO don't return password
