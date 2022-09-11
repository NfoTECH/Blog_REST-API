package com.fortunate.blogrestfulapi.service;

import com.fortunate.blogrestfulapi.dto.*;
import com.fortunate.blogrestfulapi.model.Post;
import com.fortunate.blogrestfulapi.response.*;

public interface PostService {

    CreatePostResponse createPost (PostDto postDto);

    PostResponse getAllPosts(int pageNo, int pageSize, String sortBy, String sortDir);

    CommentResponse comment(Long user_id, Long post_id, CommentDto commentDto);

    Post findPostById(Long id);

    LikeResponse like (Long user_id, Long post_id, LikeDto likeDto);
    SearchPostResponse searchPost (String keyword);
    SearchCommentResponse searchComment (String keyword);

    DeletePostResponse deletePost (Long id);
}