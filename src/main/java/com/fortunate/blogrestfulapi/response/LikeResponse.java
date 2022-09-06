package com.fortunate.blogrestfulapi.response;


import com.fortunate.blogrestfulapi.model.Like;
import com.fortunate.blogrestfulapi.model.Post;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data @AllArgsConstructor
public class LikeResponse {
    private String message;
    private LocalDateTime timeStamp;
    private Post post;
    private Like like;
    private int totalLikes;

}
