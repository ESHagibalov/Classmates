package com.classmates.demo.models;

import com.classmates.demo.repository.UserRepository;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Objects;


@Entity
@Getter
@Setter
@ToString
@Table(name = "postinfo")
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;



    public Post(String content, User user, String path) {
        this.content = content;
        this.user = user;
        this.path = path;
    }

    @NotNull
    @Setter (AccessLevel.PUBLIC)
    @Schema(description = "Text content in post", required = true)
    @JsonProperty("content")
    @Size(max = 120)
    private String content;

    @Setter(AccessLevel.PUBLIC)
    @ToString.Exclude
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false)
    private User user;

    @Column(nullable = false, name = "path", length = 64)
    private String path;

    @Transient
    public String getPhotosImagePath() {
        if (path == null || id == null) return null;

        return "/user-photos/" + id + "/" + path;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Post post = (Post) o;
        return id.equals(post.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    public Post() {
    }
}
