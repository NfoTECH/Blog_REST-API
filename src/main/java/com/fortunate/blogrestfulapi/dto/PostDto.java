package com.fortunate.blogrestfulapi.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class PostDto {
    private String title;
    private String content;
    private String featuredImage;
    private Long user_id;

}
