package com.fortunate.blogrestfulapi.controller;

import com.fortunate.blogrestfulapi.dto.LoginDto;
import com.fortunate.blogrestfulapi.response.LoginResponse;
import com.fortunate.blogrestfulapi.service.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import static org.springframework.http.HttpStatus.ACCEPTED;

@RestController
@Slf4j
@AllArgsConstructor
@RequestMapping(value = "/api/user")
public class LoginController {
    private final UserService userService;

    @GetMapping(value = "/login")
    public ResponseEntity<LoginResponse> loginResponse(@RequestBody LoginDto loginDto) {
        log.info("Successfully logged in {} ", loginDto.getEmail());
        return new ResponseEntity<>(userService.login(loginDto), ACCEPTED);
    }
}