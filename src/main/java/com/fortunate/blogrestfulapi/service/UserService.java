package com.fortunate.blogrestfulapi.service;

import com.fortunate.blogrestfulapi.dto.*;
import com.fortunate.blogrestfulapi.response.*;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
    RegisterResponse register (UserDto userDto);
    LoginResponse login (LoginDto loginDto);
    CreatePostResponse createPost (PostDto postDto);
    CommentResponse comment (int user_id, int post_id, CommentDto commentDto);
    LikeResponse like (int user_id, int post_id, LikeDto likeDto);
    SearchPostResponse searchPost (String keyword);
    SearchCommentResponse searchComment (String keyword);
}
