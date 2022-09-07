package com.fortunate.blogrestfulapi.response;

import com.fortunate.blogrestfulapi.model.Post;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

@Data @AllArgsConstructor @EqualsAndHashCode
public class CreatePostResponse {
    private  String message;
    private LocalDateTime timeStamp;
    private Post post;
}
