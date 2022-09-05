package com.fortunate.blogrestfulapi.dto;

import com.fortunate.blogrestfulapi.model.Post;
import com.fortunate.blogrestfulapi.model.User;
import lombok.Data;

@Data
public class LikeDto {
    boolean isLiked;
}
