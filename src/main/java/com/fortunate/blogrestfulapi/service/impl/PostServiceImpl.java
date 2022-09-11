package com.fortunate.blogrestfulapi.service.impl;

import com.fortunate.blogrestfulapi.dto.*;
import com.fortunate.blogrestfulapi.exception.PostAlreadyLikedException;
import com.fortunate.blogrestfulapi.model.Comment;
import com.fortunate.blogrestfulapi.model.Like;
import com.fortunate.blogrestfulapi.model.Post;
import com.fortunate.blogrestfulapi.model.User;
import com.fortunate.blogrestfulapi.repository.CommentRepository;
import com.fortunate.blogrestfulapi.repository.LikeRepository;
import com.fortunate.blogrestfulapi.repository.PostRepository;
import com.fortunate.blogrestfulapi.response.*;
import com.fortunate.blogrestfulapi.service.PostService;
import com.fortunate.blogrestfulapi.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.text.Normalizer;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Locale;
import java.util.regex.Pattern;

@Service
@AllArgsConstructor
public class PostServiceImpl implements PostService {
    private final PostRepository postRepository;
    private final CommentRepository commentRepository;
    private final LikeRepository likeRepository;
    private final UserService userService;


    private static final Pattern NONLATIN = Pattern.compile("[^\\w-]");
    private static final Pattern WHITESPACE = Pattern.compile("[\\s]");

    @Override
    public CreatePostResponse createPost(PostDto postDto) {
        Post newPost = new Post();
        User user = userService.findUserById(postDto.getUser_id());
        newPost.setTitle(postDto.getTitle());
        newPost.setContent(postDto.getContent());
        newPost.setFeaturedImage(postDto.getFeaturedImage());
        newPost.setSlug(createSlug(postDto.getTitle()));
        newPost.setUser(user);
        postRepository.save(newPost);
        return new CreatePostResponse("success", LocalDateTime.now(), newPost);

    }

    @Override
    public PostResponse getAllPosts(int pageNo, int pageSize, String sortBy, String sortDir) {

        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending()
                : Sort.by(sortBy).descending();
        // create Pageable instance
        Pageable pageable = PageRequest.of(pageNo, pageSize, sort);
        Page<Post> posts = postRepository.findAll(pageable);
        PostResponse postResponse = new PostResponse();
        postResponse.setPageNo(posts.getNumber());
        postResponse.setPageSize(posts.getSize());
        postResponse.setTotalElements(posts.getTotalElements());
        postResponse.setTotalPages(posts.getTotalPages());
        postResponse.setLast(posts.isLast());
        return postResponse;
    }

    @Override
    public CommentResponse comment(Long user_id, Long post_id, CommentDto commentDto) {
        Comment comment = new Comment();
        User user = userService.findUserById(user_id);
        Post post = findPostById(post_id);
        comment.setComment(commentDto.getComment());
        comment.setUser(user);
        comment.setPost(post);
        commentRepository.save(comment);
        return new CommentResponse("success", comment, LocalDateTime.now(), post);
    }

    @Override
    public Post findPostById(Long id) {
        return postRepository.findById(id).orElseThrow(() -> new RuntimeException("Post not found"));
    }


    @Override
    public LikeResponse like(Long user_id, Long post_id, LikeDto likeDto) {
        Like like = new Like();
        User user = userService.findUserById(user_id);
        Post post = findPostById(post_id);

        Like duplicateLikes = likeRepository.findLikeByUserIdAndPostId(user_id, post_id);
        LikeResponse likeResponse;
        if (duplicateLikes == null) {
            like.setLiked(likeDto.isLiked());
            like.setUser(user);
            like.setPost(post);
            likeRepository.save(like);
            List<Like> likes = likeRepository.likes(post_id);
            likeResponse = new LikeResponse("success", LocalDateTime.now(), post, like, likes.size());
        } else {
            likeRepository.delete(duplicateLikes);
            throw new PostAlreadyLikedException("Post already liked. Now unliked!");
        }
        return likeResponse;
    }

    @Override
    public SearchPostResponse searchPost(String keyword) {
        List<Post> posts = postRepository.findByTitleContainingIgnoreCase(keyword);
        return  new SearchPostResponse("success", LocalDateTime.now(), posts);
    }

    @Override
    public SearchCommentResponse searchComment(String keyword) {
        List<Comment> comments = commentRepository.findByCommentContainingIgnoreCase(keyword);
        return  new SearchCommentResponse("success", LocalDateTime.now(), comments);

    }

    @Override
    public DeletePostResponse deletePost(Long id) {
        Post post = findPostById(id);
        postRepository.delete(post);
        return new DeletePostResponse("success", LocalDateTime.now(), post);
    }

    public String createSlug(String input) {
        String noWhiteSpace = WHITESPACE.matcher(input).replaceAll("-");
        String normalized = Normalizer.normalize(noWhiteSpace, Normalizer.Form.NFD);
        String slug = NONLATIN.matcher(normalized).replaceAll("");
        return slug.toLowerCase(Locale.ENGLISH);
    }
}
