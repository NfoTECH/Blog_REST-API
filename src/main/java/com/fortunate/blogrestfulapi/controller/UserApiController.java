package com.fortunate.blogrestfulapi.controller;

import com.fortunate.blogrestfulapi.dto.UserDto;
import com.fortunate.blogrestfulapi.response.RegisterResponse;
import com.fortunate.blogrestfulapi.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

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
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/register").toUriString());
        return ResponseEntity.created(uri).body( userService.register(userDto));
    }
}
