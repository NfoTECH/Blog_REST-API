package com.fortunate.blogrestfulapi.service.impl;

import com.fortunate.blogrestfulapi.dto.*;
import com.fortunate.blogrestfulapi.exception.UserNotFoundException;
import com.fortunate.blogrestfulapi.model.User;
import com.fortunate.blogrestfulapi.repository.UserRepository;
import com.fortunate.blogrestfulapi.response.*;
import com.fortunate.blogrestfulapi.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.Optional;

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
        User user = userRepository.findUserByEmail(loginDto.getEmail())
                .orElseThrow(()->new UserNotFoundException("User not found"));
        if (!(user.getPassword().equals(loginDto.getPassword()))){
            return new LoginResponse("password MisMatch", LocalDateTime.now());
        }
        return new LoginResponse("success", LocalDateTime.now());
    }

    @Override
    public User findUserById(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
    }
}
