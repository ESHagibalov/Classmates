package com.classmates.demo.payload.request;

import lombok.Data;

@Data
public class LoginRequest {
    private String username;

    private String password;
}