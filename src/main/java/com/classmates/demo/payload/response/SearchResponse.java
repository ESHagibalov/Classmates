package com.classmates.demo.payload.response;

import com.classmates.demo.models.Subscriber;
import com.classmates.demo.models.User;
import lombok.Data;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Optional;
import java.util.Set;


@Data
public class SearchResponse {

    private Long id;

    private String username;

    private Set<Subscriber> subscriptions = new HashSet<>();

    private Set<Subscriber> subscribers = new HashSet<>();

    public SearchResponse(Optional<User> user) {
        this.username = user.get().getUsername();
        this.id = user.get().getId();
        for( User u : user.get().getSubscriptions()) {
            this.subscriptions.add(new Subscriber(u.getId(), u.getUsername()));
        }
        for( User u : user.get().getSubscribers()) {
           this.subscribers.add(new Subscriber(u.getId(), u.getUsername()));
        }
    }

}
