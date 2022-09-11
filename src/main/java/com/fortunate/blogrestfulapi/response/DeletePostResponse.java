package com.fortunate.blogrestfulapi.response;

import com.fortunate.blogrestfulapi.model.Post;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data @AllArgsConstructor
public class DeletePostResponse {
    private String message;
    private LocalDateTime timeStamp;
    private Post post;
}
