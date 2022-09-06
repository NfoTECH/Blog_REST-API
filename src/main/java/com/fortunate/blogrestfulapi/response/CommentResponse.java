package com.fortunate.blogrestfulapi.response;

import com.fortunate.blogrestfulapi.model.Comment;
import com.fortunate.blogrestfulapi.model.Post;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data @AllArgsConstructor
public class CommentResponse {
    private String message;
    private Comment comment;
    private LocalDateTime timeStamp;
    private Post post;
}
