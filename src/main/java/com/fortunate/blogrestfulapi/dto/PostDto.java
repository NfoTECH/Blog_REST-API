package com.fortunate.blogrestfulapi.dto;

import lombok.Data;

@Data
public class PostDto {
    private String title;
    private String content;
    private String featuredImage;
    private Long user_id;
}
