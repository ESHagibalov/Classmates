package com.classmates.demo.controllers;

import com.classmates.demo.models.User;
import com.classmates.demo.payload.request.SearchRequest;
import com.classmates.demo.payload.response.SearchResponse;
import com.classmates.demo.repository.UserRepository;
import com.classmates.demo.service.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Set;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/profile")
public class ProfileController {

    private final ProfileService profileService;

    @Autowired
    UserRepository userRepository;

    @Autowired
    public ProfileController(ProfileService profileService) {
        this.profileService = profileService;
    }

    @GetMapping("/get/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public User getProfileInfo(@PathVariable("id") User user) {
        return user;
    }

    @PostMapping("/subscribe/{channelId}")
    public User changeSubscription(
            @PathVariable("channelId") User channel,
            @Valid @RequestBody SearchRequest searchRequest
    ) {
        if (channel.equals(userRepository.findByUsername(searchRequest.getUsername()))) {
            return channel;
        } else {
            return profileService.changeSubscription(channel, userRepository.findByUsername(searchRequest.getUsername()));
        }
    }//TODO don't show role, subscribers, email and password of subscriber


}
