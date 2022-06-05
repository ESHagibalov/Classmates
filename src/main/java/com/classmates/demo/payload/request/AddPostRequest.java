package com.classmates.demo.payload.request;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class AddPostRequest {
    MultipartFile image;
    String content;
}
