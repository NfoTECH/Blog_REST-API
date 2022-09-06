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
import com.fortunate.blogrestfulapi.repository.UserRepository;
import com.fortunate.blogrestfulapi.response.*;
import com.fortunate.blogrestfulapi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.Normalizer;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Locale;
import java.util.regex.Pattern;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PostRepository postRepository;
    private final CommentRepository commentRepository;
    private final LikeRepository likeRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, PostRepository postRepository, CommentRepository commentRepository, LikeRepository likeRepository) {
        this.userRepository = userRepository;
        this.postRepository = postRepository;
        this.commentRepository = commentRepository;
        this.likeRepository = likeRepository;
    }


    private static final Pattern NONLATIN = Pattern.compile("[^\\w-]");
    private static final Pattern WHITESPACE = Pattern.compile("[\\s]");
    @Override
    public RegisterResponse register(UserDto userDto) {
        User user = new User();
        user.setName(user.getName());
        user.setEmail(user.getEmail());
        user.setPassword(userDto.getPassword());
        user.setRole(userDto.getRole());
        userRepository.save(user);
        return new RegisterResponse("success", LocalDateTime.now(), user);
    }

    @Override
    public LoginResponse login(LoginDto loginDto) {
        User loginUser = findUserByEmail(loginDto.getEmail());
        LoginResponse loginResponse = null;
        if (loginUser != null) {
            if (loginUser.getPassword().equals(loginDto.getPassword())) {
                loginResponse = new LoginResponse("success", LocalDateTime.now());
            } else {
                loginResponse = new LoginResponse("password Mismatch", LocalDateTime.now());
            }
        }
        return loginResponse;
    }

    private User findUserByEmail(String email) {
        return userRepository.findUserByEmail(email).orElseThrow(() -> new RuntimeException("User not found"));
    }

    @Override
    public CreatePostResponse createPost(PostDto postDto) {
        Post newPost = new Post();
        User user = findUserById(postDto.getUser_id());
        newPost.setTitle(postDto.getTitle());
        newPost.setContent(postDto.getContent());
        newPost.setFeaturedImage(postDto.getContent());
        newPost.setSlug(createSlug(postDto.getTitle()));
        newPost.setUser(user);
        postRepository.save(newPost);
        return new CreatePostResponse("success", LocalDateTime.now(), newPost);

    }

    private User findUserById(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
    }

    @Override
    public CommentResponse comment(Long user_id, Long post_id, CommentDto commentDto) {
        Comment comment = new Comment();
        User user = findUserById(user_id);
        Post post = findPostById(post_id);
        comment.setComment(commentDto.getComment());
        comment.setUser(user);
        comment.setPost(post);
        commentRepository.save(comment);
        return new CommentResponse("success", comment, LocalDateTime.now(), post);
    }

    private Post findPostById(Long id) {
        return postRepository.findById(id).orElseThrow(() -> new RuntimeException("Post not found"));
    }


    @Override
    public LikeResponse like(Long user_id, Long post_id, LikeDto likeDto) {
        Like like = new Like();
        User user = findUserById(user_id);
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
        List<Post> posts = postRepository.findByTitleContaining(keyword);
        return  new SearchPostResponse("success", LocalDateTime.now(), posts);
    }

    @Override
    public SearchCommentResponse searchComment(String keyword) {
        List<Comment> comments = commentRepository.findByCommentContaining(keyword);
        return  new SearchCommentResponse("success", LocalDateTime.now(), comments);

    }

    public String createSlug(String input) {
        String nowhitespace = WHITESPACE.matcher(input).replaceAll("-");
        String normalized = Normalizer.normalize(nowhitespace, Normalizer.Form.NFD);
        String slug = NONLATIN.matcher(normalized).replaceAll("");
        return slug.toLowerCase(Locale.ENGLISH);
    }
}
