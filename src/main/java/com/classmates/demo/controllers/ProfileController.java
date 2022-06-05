package com.classmates.demo.controllers;

import com.classmates.demo.Utils.FileUploadUtil;
import com.classmates.demo.models.Post;
import com.classmates.demo.models.PostModel;
import com.classmates.demo.models.User;
import com.classmates.demo.payload.response.GetUserPosts;
import com.classmates.demo.payload.response.MessageResponse;
import com.classmates.demo.payload.response.SearchResponse;
import com.classmates.demo.repository.PostsRepository;
import com.classmates.demo.repository.UserRepository;
import com.classmates.demo.service.PostsService;
import com.classmates.demo.service.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;
import java.security.Principal;
import java.util.HashSet;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/profile")
public class ProfileController {

    private final ProfileService profileService;
    private final PostsService postsService;

    @Autowired
    UserRepository userRepository;

    @Autowired
    PostsRepository postsRepository;

    @Autowired
    public ProfileController(ProfileService profileService, PostsService postsService) {
        this.profileService = profileService;
        this.postsService = postsService;
    }

    @GetMapping("/get/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<SearchResponse> getProfileInfo(@PathVariable("id") User user) {
        return ResponseEntity.ok(new SearchResponse(Optional.ofNullable(user)));
    }

    @PostMapping("/subscribe/{channelId}")
    public ResponseEntity<SearchResponse> changeSubscription(
            @PathVariable("channelId") Long channelId,
            Principal current
    ) {
        String username = current.getName();
        User subscriber = userRepository.findByUsername(username).get();
        Optional<User> channelTmp = userRepository.findById(channelId);
        User channel = channelTmp.get();
        User tmp;
        if (channel.equals(subscriber)) {
            tmp = channel;
        } else {
            tmp = profileService.changeSubscription(channel, subscriber);
        }
        return ResponseEntity.ok(new SearchResponse(Optional.ofNullable(tmp)));
    }

    @GetMapping("/post/get/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> getOtherUsersPosts(@PathVariable("id") User user) {
        Set<PostModel> posts = new HashSet<>();
        for (Post p : user.getPosts()) {
            posts.add(new PostModel(p.getId(), p.getContent(), p.getUser().getId(), p.getPath()));
        }
        return ResponseEntity.ok(new GetUserPosts(posts));
    }


    @GetMapping("/post/get_my")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> getOtherUsersPosts(Principal current) {
        User user = userRepository.findByUsername(current.getName()).get();
        Set<PostModel> posts = new HashSet<>();
        for (Post p : user.getPosts()) {
            posts.add(new PostModel(p.getId(), p.getContent(), p.getUser().getId(), p.getPath()));
        }
        return ResponseEntity.ok(new GetUserPosts(posts));
    }

    @GetMapping("/post/delete_my/{postId}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> deleteMyPost(Principal current, @PathVariable("postId") Post post) {
        User user = userRepository.findByUsername(current.getName()).get();
        try {
            if (postsRepository.findById(post.getId()).isPresent()) {
                if (Objects.equals(postsRepository.findById(post.getId()).get().getUser().getId(), user.getId())) {
                    postsRepository.deleteById(post.getId());
                } else {
                    return ResponseEntity.ok(new MessageResponse("You can't delete this post, bruh"));
                }
            } else {
                return ResponseEntity.ok(new MessageResponse("No posts with id" + post.getId()));
            }
        } catch (Exception e) {
            return ResponseEntity.ok(new MessageResponse(e.getMessage()));
        }//TODO 500 error post id don't alive
        Set<PostModel> posts = new HashSet<>();
        for (Post p : user.getPosts()) {
            posts.add(new PostModel(p.getId(), p.getContent(), p.getUser().getId(), p.getPath()));
        }
        return ResponseEntity.ok(new GetUserPosts(posts));
    }

    @PostMapping("/post/add_post")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> addPost(
            @RequestParam String content,
            @RequestParam ("image") MultipartFile image,
            Principal current
    ) throws IOException {
        String fileName = StringUtils.cleanPath(Objects.requireNonNull(image.getOriginalFilename()));

        User currentUser = userRepository.findByUsername(current.getName()).get();
        Post p = new Post(content, currentUser, fileName);
        p.setUser(currentUser);
        postsRepository.save(p);
        String uploadDir = "user-photos/" + p.getId();

        FileUploadUtil.saveFile(uploadDir, fileName, image);

        return ResponseEntity.ok(new MessageResponse("Post successfully shared"));
    }

}
