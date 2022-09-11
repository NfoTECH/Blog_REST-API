package com.fortunate.blogrestfulapi.controller;

import com.fortunate.blogrestfulapi.dto.*;
import com.fortunate.blogrestfulapi.response.*;
import com.fortunate.blogrestfulapi.service.PostService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.*;

@RestController
@Slf4j
@AllArgsConstructor
@RequestMapping(value = "/api")
public class LikeController {
    private final PostService postService;

    @PostMapping(value = "/like/user-id/{user_id}/post-id/{post_id}")
    public ResponseEntity<LikeResponse> like(@PathVariable(value = "user_id") Long user_id,
                                             @PathVariable(value = "post_id") Long post_id,
                                             @RequestBody LikeDto likeDto) {
        log.info("Successfully liked : {} ", likeDto.isLiked());
        return new ResponseEntity<>(postService.like(user_id, post_id, likeDto), ACCEPTED);
    }
}