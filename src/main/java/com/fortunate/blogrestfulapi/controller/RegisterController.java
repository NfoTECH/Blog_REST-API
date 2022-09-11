package com.fortunate.blogrestfulapi.controller;

import com.fortunate.blogrestfulapi.dto.UserDto;
import com.fortunate.blogrestfulapi.response.RegisterResponse;
import com.fortunate.blogrestfulapi.service.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import static org.springframework.http.HttpStatus.CREATED;

@RestController
@Slf4j
@AllArgsConstructor
@RequestMapping(value = "/api/user")
public class RegisterController {
    private final UserService userService;
    @PostMapping(value = "/register")
    public ResponseEntity<RegisterResponse> registerResponse(@RequestBody UserDto userDto) {
        log.info("Successfully registered {} ", userDto.getEmail());
        return new ResponseEntity<>(userService.register(userDto), CREATED);
    }
}
