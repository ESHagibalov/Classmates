package com.classmates.demo.service;

import com.classmates.demo.models.User;
import com.classmates.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

@Service
public class ProfileService {

    private final UserRepository userRepository;

    @Autowired
    public ProfileService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User changeSubscription(User channel, Optional<User> subscriber) {
        Set<User> subscribers = channel.getSubscribers();

        if (subscribers.contains(subscriber.get())) {
            subscribers.remove(subscriber.get());
        } else {
            subscribers.add(subscriber.get());
        }

        return userRepository.save(channel);
    }
}
