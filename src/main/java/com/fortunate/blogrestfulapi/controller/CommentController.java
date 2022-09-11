package com.fortunate.blogrestfulapi.controller;

import com.fortunate.blogrestfulapi.dto.*;
import com.fortunate.blogrestfulapi.response.*;
import com.fortunate.blogrestfulapi.service.PostService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import static org.springframework.http.HttpStatus.*;
import static org.springframework.http.HttpStatus.FOUND;

@RestController
@Slf4j
@AllArgsConstructor
@RequestMapping(value = "/api")
public class CommentController {
    private final PostService postService;

    @PostMapping(value = "/comment/user-id/{user_id}/post-id/{post_id}")
    public ResponseEntity<CommentResponse> comment(@PathVariable(value = "user_id") Long user_id,
                                                   @PathVariable(value = "post_id") Long post_id,
                                                   @RequestBody CommentDto commentDto) {
        log.info("Successfully commented :  {} ", commentDto.getComment());
        return new ResponseEntity<>(postService.comment(user_id, post_id, commentDto), CREATED);
    }

    @GetMapping(value = "/search-comment/{keyword}")
    public ResponseEntity<SearchCommentResponse> searchComment(@PathVariable(value = "keyword") String keyword) {
        return new ResponseEntity<>(postService.searchComment(keyword), FOUND);
    }
}