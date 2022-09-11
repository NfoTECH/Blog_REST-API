package com.fortunate.blogrestfulapi.service.impl;

import com.fortunate.blogrestfulapi.dto.CommentDto;
import com.fortunate.blogrestfulapi.dto.LikeDto;
import com.fortunate.blogrestfulapi.dto.PostDto;
import com.fortunate.blogrestfulapi.model.Comment;
import com.fortunate.blogrestfulapi.model.Like;
import com.fortunate.blogrestfulapi.model.Post;
import com.fortunate.blogrestfulapi.model.User;
import com.fortunate.blogrestfulapi.repository.CommentRepository;
import com.fortunate.blogrestfulapi.repository.LikeRepository;
import com.fortunate.blogrestfulapi.repository.PostRepository;
import com.fortunate.blogrestfulapi.repository.UserRepository;
import com.fortunate.blogrestfulapi.response.CommentResponse;
import com.fortunate.blogrestfulapi.response.CreatePostResponse;
import com.fortunate.blogrestfulapi.response.LikeResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static java.time.Month.SEPTEMBER;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
class PostServiceImplTest {

    @Mock
    private UserRepository userRepository;
    @Mock
    private LikeRepository likeRepository;
    @Mock
    private CommentRepository commentRepository;
    @Mock
    private PostRepository postRepository;

    @InjectMocks
    private PostServiceImpl postService;

    private LocalDateTime localDateTime;
    private User user;
    private Comment comment;
    private Like like;
    private Post post;
    List<Like> likeList = new ArrayList<>();
    List<Post> postList = new ArrayList<>();
    List<Comment> commentList = new ArrayList<>();
    List<User> userList = new ArrayList<>();

    @BeforeEach
    void setUp() {
        localDateTime = LocalDateTime.of(2022, SEPTEMBER,3,6,30,40,50000);
        user = new User(1L, "Uzonna" , "uzo@gmail.com" , "user" , "1234" , localDateTime ,
                localDateTime  , postList , commentList , likeList);
        post = new Post(2L, "Test my blog" , "Test the endpoints of my blog" , "test-my-blog"
                , "blog.png", localDateTime , localDateTime , user, commentList , likeList);
        like = new Like(1L, true , localDateTime , localDateTime , user , post);
        comment = new Comment(1L, "It is well" , localDateTime , localDateTime , post , user);
    }


    @Test
    void createPost() {
        when(userRepository.findById(1L)).thenReturn(Optional.ofNullable(user));
        PostDto postDto = new PostDto( "Test my blog" , "Test the endpoints of my blog" ,
                "blog.png", 1L);
        CreatePostResponse createPostResponse = new CreatePostResponse("success" ,
                localDateTime , post);
        var actual = postService.createPost(postDto);
        actual.setTimeStamp(localDateTime);
        actual.getPost().setCreatedAt(localDateTime);
        actual.getPost().setUpdatedAt(localDateTime);
        actual.getPost().setId(2L);
        actual.getPost().setSlug("test-my-blog");
        assertEquals(createPostResponse , actual);
    }

    @Test
    void comment() {
    }

    @Test
    void findPostById() {
        when(postRepository.findById(1L)).thenReturn(Optional.ofNullable(post));
        assertEquals(post , postService.findPostById(1L));
    }

    @Test
    void like() {
        when(userRepository.findById(1L)).thenReturn(Optional.ofNullable(user));
        when(postRepository.findById(1L)).thenReturn(Optional.ofNullable(post));
        List<Like> likes = new ArrayList<>(Arrays.asList(like));
        when(likeRepository.likes(1L)).thenReturn(likes);
        LikeDto likeDto = new LikeDto(true);
        LikeResponse likeResponse = new LikeResponse("success" , localDateTime, post , like ,
                1);
        var actual = postService.like(1L , 1L  , likeDto);
        actual.setTimeStamp(localDateTime);
        actual.setLike(like);
        likeResponse.getLike().setUser(user);
        likeResponse.getLike().setPost(post);
        assertEquals(likeResponse , actual);
    }

    @Test
    void searchPost() {
    }

    @Test
    void searchComment() {
        when(userRepository.findById(1L)).thenReturn(Optional.ofNullable(user));
        when(postRepository.findById(1L)).thenReturn(Optional.ofNullable(post));
        CommentDto commentDto = new CommentDto("Interesting");
        CommentResponse commentResponse = new CommentResponse("success" , comment ,
                localDateTime , post);
        var actual = postService.comment(1L, 1L, commentDto);
        actual.setTimeStamp(localDateTime);
        actual.setComment(comment);
        commentResponse.setComment(comment);
        commentResponse.setPost(post);
        assertEquals(commentResponse , actual);
    }

    @Test
    void deletePost() {
        when(postRepository.findById(1L)).thenReturn(Optional.ofNullable(post));
        assertEquals("Post deleted successfully" , postService.deletePost(1L));
    }
}