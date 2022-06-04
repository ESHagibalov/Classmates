package com.classmates.demo.controllers;

import com.classmates.demo.payload.request.SearchRequest;
import com.classmates.demo.payload.response.SearchResponse;
import com.classmates.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/search")
public class SearchController {

    @Autowired
    UserRepository userRepository;

    @PostMapping("/user")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> searchUser(@Valid @RequestBody SearchRequest searchRequest) {
         return ResponseEntity.ok(new SearchResponse(userRepository.findByUsername(searchRequest.getUsername())));
    }
}
