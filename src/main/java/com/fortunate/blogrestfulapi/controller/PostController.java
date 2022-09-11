package com.fortunate.blogrestfulapi.controller;

import com.fortunate.blogrestfulapi.dto.*;
import com.fortunate.blogrestfulapi.model.Post;
import com.fortunate.blogrestfulapi.response.*;
import com.fortunate.blogrestfulapi.service.PostService;
import com.fortunate.blogrestfulapi.utils.AppConstants;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import static org.springframework.http.HttpStatus.*;

@RestController
@Slf4j
@AllArgsConstructor
@RequestMapping(value = "/api/")
public class PostController {
    private final PostService postService;

    @PostMapping(value = "/create-post")
    public ResponseEntity<CreatePostResponse> createPostResponse(@RequestBody PostDto postDto) {
        log.info("Successfully created {} ", postDto.getTitle());
        return new ResponseEntity<>(postService.createPost(postDto), CREATED);
    }

    @GetMapping("/posts")
    public PostResponse getAllPosts(
            @RequestParam(value = "pageNo", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER, required = false) int pageNo,
            @RequestParam(value = "pageSize", defaultValue = AppConstants.DEFAULT_PAGE_SIZE, required = false) int pageSize,
            @RequestParam(value = "sortBy", defaultValue = AppConstants.DEFAULT_SORT_BY, required = false) String sortBy,
            @RequestParam(value = "sortDir", defaultValue = AppConstants.DEFAULT_SORT_DIRECTION, required = false) String sortDir) {

        return postService.getAllPosts(pageNo, pageSize, sortBy, sortDir);
    }


    @GetMapping(value = "/search-post/{keyword}")
    public ResponseEntity<SearchPostResponse> searchPost(@PathVariable(value = "keyword") String keyword) {
        return new ResponseEntity<>(postService.searchPost(keyword), FOUND);
    }

    @GetMapping(value = "post/{id}")
    public ResponseEntity<Post> getPost(@PathVariable(value = "id") Long id) {
        return ResponseEntity.ok().body(postService.findPostById(id));
    }

    @GetMapping(value = "delete-post/{id}")
    public ResponseEntity<DeletePostResponse> deletePost(@PathVariable(value = "id") Long id) {
        return new ResponseEntity<>(postService.deletePost(id), ACCEPTED);
    }
}