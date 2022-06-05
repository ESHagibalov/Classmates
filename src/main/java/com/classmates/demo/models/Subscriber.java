package com.classmates.demo.models;

import lombok.Data;

@Data
public class Subscriber {
    private Long id;
    private String username;

    public Subscriber() {
    }

    public Subscriber(Long id, String username) {
        this.id = id;
        this.username = username;
    }
}
