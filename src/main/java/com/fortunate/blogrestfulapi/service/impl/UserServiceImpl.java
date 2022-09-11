package com.fortunate.blogrestfulapi.service.impl;

import com.fortunate.blogrestfulapi.dto.*;
import com.fortunate.blogrestfulapi.model.User;
import com.fortunate.blogrestfulapi.repository.UserRepository;
import com.fortunate.blogrestfulapi.response.*;
import com.fortunate.blogrestfulapi.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public RegisterResponse register(UserDto userDto) {
        User user = User.builder()
                .name(userDto.getName())
                .email(userDto.getEmail())
                .role("USER")
                .password(userDto.getPassword())
                .build();
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
                loginResponse = new LoginResponse("password MisMatch", LocalDateTime.now());
            }
        }
//        if (loginUser == null) {
//            return new LoginResponse("user not found", LocalDateTime.now());
//        }
//        if (!loginUser.getPassword().equals(loginDto.getPassword())) {
//            return new  LoginResponse("mismatch", LocalDateTime.now());
//        }
//        loginResponse = new LoginResponse("success", LocalDateTime.now());
        return loginResponse;
    }

    @Override
    public User findUserByEmail(String email) {
        return userRepository.findUserByEmail(email).orElseThrow(() -> new RuntimeException("User not found"));
    }

    @Override
    public User findUserById(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
    }
}
