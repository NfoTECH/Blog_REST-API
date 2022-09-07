package com.fortunate.blogrestfulapi.controller;

import com.fortunate.blogrestfulapi.dto.CommentDto;
import com.fortunate.blogrestfulapi.dto.LikeDto;
import com.fortunate.blogrestfulapi.dto.PostDto;
import com.fortunate.blogrestfulapi.dto.UserDto;
import com.fortunate.blogrestfulapi.model.Post;
import com.fortunate.blogrestfulapi.response.*;
import com.fortunate.blogrestfulapi.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

import static org.springframework.http.HttpStatus.*;

@RestController @Slf4j
@RequestMapping(value = "/api/user")
public class UserApiController {
    private final UserService userService;

    @Autowired
    public UserApiController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping(value = "/register")
    public ResponseEntity<RegisterResponse> registerResponse (@RequestBody UserDto userDto) {
        log.info("Successfully registered {} ", userDto.getEmail());
        return new ResponseEntity<>(userService.register(userDto) , CREATED);
    }

    @PostMapping(value = "/create_post")
    public ResponseEntity<CreatePostResponse> createPostResponse (@RequestBody PostDto postDto) {
        log.info("Successfully registered {} ", postDto.getTitle());
        return new ResponseEntity<>(userService.createPost(postDto), CREATED);
    }

    @PostMapping(value = "/comment/{user_id}/{post_id}")
    public ResponseEntity<CommentResponse> comment(@PathVariable(value = "user_id") Long user_id,
                                                   @PathVariable(value = "post_id") Long post_id,
                                                   @RequestBody CommentDto commentDto){
        log.info("Successfully commented :  {} " , commentDto.getComment());
        return new ResponseEntity<>( userService.comment(user_id , post_id , commentDto) , CREATED);
    }

    @PostMapping(value = "/like/{user_id}/{post_id}")
    public ResponseEntity<LikeResponse> like(@PathVariable(value = "user_id") Long user_id,
                                             @PathVariable(value = "post_id") Long post_id,
                                             @RequestBody LikeDto likeDto) {
        log.info("Successfully liked : {} ", likeDto.isLiked());
        return new ResponseEntity<>(userService.like(user_id, post_id, likeDto), ACCEPTED);
    }

    @GetMapping(value = "/searchPost/{keyword}")
    public ResponseEntity<SearchPostResponse> searchPost(@PathVariable(value = "keyword") String keyword) {
//        log.info("Successfully searched for : {} ", keyword);
        return new ResponseEntity<>(userService.searchPost(keyword), FOUND);
    }

    @GetMapping(value = "/searchComment/{keyword}")
    public ResponseEntity<SearchCommentResponse> searchComment(@PathVariable(value = "keyword") String keyword) {
//        log.info("Successfully searched for : {} ", keyword);
        return new ResponseEntity<>(userService.searchComment(keyword), FOUND);
    }

    @GetMapping(value = "post/{id}")
    public ResponseEntity<Post> getPost(@PathVariable(value = "id") Long id) {
        return ResponseEntity.ok().body(userService.findPostById(id));
    }


}
