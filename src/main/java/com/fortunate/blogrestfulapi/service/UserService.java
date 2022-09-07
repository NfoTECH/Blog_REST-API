package com.fortunate.blogrestfulapi.service;

import com.fortunate.blogrestfulapi.dto.*;
import com.fortunate.blogrestfulapi.model.Post;
import com.fortunate.blogrestfulapi.model.User;
import com.fortunate.blogrestfulapi.response.*;
import org.springframework.stereotype.Service;

public interface UserService {
    RegisterResponse register (UserDto userDto);
    LoginResponse login (LoginDto loginDto);

    User findUserByEmail(String email);

    CreatePostResponse createPost (PostDto postDto);

    User findUserById(Long id);

    CommentResponse comment(Long user_id, Long post_id, CommentDto commentDto);

    Post findPostById(Long id);

    LikeResponse like (Long user_id, Long post_id, LikeDto likeDto);
    SearchPostResponse searchPost (String keyword);
    SearchCommentResponse searchComment (String keyword);
}
