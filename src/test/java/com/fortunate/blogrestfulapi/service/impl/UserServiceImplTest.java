package com.fortunate.blogrestfulapi.service.impl;

import com.fortunate.blogrestfulapi.dto.*;
import com.fortunate.blogrestfulapi.model.Comment;
import com.fortunate.blogrestfulapi.model.Like;
import com.fortunate.blogrestfulapi.model.Post;
import com.fortunate.blogrestfulapi.model.User;
import com.fortunate.blogrestfulapi.repository.CommentRepository;
import com.fortunate.blogrestfulapi.repository.LikeRepository;
import com.fortunate.blogrestfulapi.repository.PostRepository;
import com.fortunate.blogrestfulapi.repository.UserRepository;
import com.fortunate.blogrestfulapi.response.*;
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
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;


@SpringBootTest
class UserServiceImplTest {

        @Mock
        private UserRepository userRepository;
        @Mock
        private LikeRepository likeRepository;
        @Mock
        private CommentRepository commentRepository;
        @Mock
        private PostRepository postRepository;

        @InjectMocks
        private UserServiceImpl userService;

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
        void register() {
            UserDto userDTO = new UserDto( "Uzonna" , "uzo@gmail.com" , "user" , "1234" );
            when(userRepository.save(user)).thenReturn(user);
            RegisterResponse registerResponse = new RegisterResponse("success" , localDateTime ,
     user);
            var actual =  userService.register(userDTO);
            actual.setTimeStamp(localDateTime);
            actual.getUser().setCreatedAt(localDateTime);
            actual.getUser().setUpdatedAt(localDateTime);
            actual.getUser().setId(1L);
            assertEquals(registerResponse , actual);
        }

        @Test
        void login_Success() {
            LoginDto loginDto = new LoginDto("uzo@gmail.com" , "1234");

     when(userRepository.findUserByEmail("uzo@gmail.com")).thenReturn(Optional.ofNullable(user));
            LoginResponse loginResponse = new LoginResponse("success" , localDateTime);
            var actual =  userService.login(loginDto);
            actual.setTimeStamp(localDateTime);
            assertEquals(loginResponse , actual);
        }

        @Test
        void login_IncorrectPassword() {
            LoginDto loginDto = new LoginDto("fortune@gmail.com" , "1238745");

     when(userRepository.findUserByEmail("fortune@gmail.com")).thenReturn(Optional.ofNullable(user));
            LoginResponse loginResponse = new LoginResponse("password MisMatch" , localDateTime);
            var actual =  userService.login(loginDto);
            actual.setTimeStamp(localDateTime);
            assertEquals(loginResponse , actual);
        }

        @Test
        void createPost() {
            when(userRepository.findById(1L)).thenReturn(Optional.ofNullable(user));
            PostDto postDto = new PostDto( "Test my blog" , "Test the endpoints of my blog" ,
     "blog.png", 1L);
            CreatePostResponse createPostResponse = new CreatePostResponse("success" ,
     localDateTime , post);
            var actual = userService.createPost(postDto);
            actual.setTimeStamp(localDateTime);
            actual.getPost().setCreatedAt(localDateTime);
            actual.getPost().setUpdatedAt(localDateTime);
            actual.getPost().setId(2L);
            actual.getPost().setSlug("test-my-blog");
            assertEquals(createPostResponse , actual);
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
            var actual = userService.like(1L , 1L  , likeDto);
            actual.setTimeStamp(localDateTime);
            actual.setLike(like);
            likeResponse.getLike().setUser(user);
            likeResponse.getLike().setPost(post);
            assertEquals(likeResponse , actual);
        }

        @Test
        void comment() {
            when(userRepository.findById(1L)).thenReturn(Optional.ofNullable(user));
            when(postRepository.findById(1L)).thenReturn(Optional.ofNullable(post));
            CommentDto commentDto = new CommentDto("Interesting");
            CommentResponse commentResponse = new CommentResponse("success" , comment ,
     localDateTime , post);
            var actual = userService.comment(1L, 1L, commentDto);
            actual.setTimeStamp(localDateTime);
            actual.setComment(comment);
            commentResponse.setComment(comment);
            commentResponse.setPost(post);
            assertEquals(commentResponse , actual);
        }

        @Test
        void findUserById() {
            when(userRepository.findById(1L)).thenReturn(Optional.ofNullable(user));
            assertEquals(user , userService.findUserById(1L));
        }

        @Test
        void findPostById() {
            when(postRepository.findById(1L)).thenReturn(Optional.ofNullable(post));
            assertEquals(post , userService.findPostById(1L));
        }

        @Test
        void findUserByEmail() {
     when(userRepository.findUserByEmail("fortunenwachukwu@gmail.com")).thenReturn(Optional.ofNullable(user));
            assertEquals(user , userService.findUserByEmail("fortunenwachukwu@gmail.com"));
        }
}