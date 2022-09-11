package com.fortunate.blogrestfulapi.service;

import com.fortunate.blogrestfulapi.dto.*;
import com.fortunate.blogrestfulapi.model.Post;
import com.fortunate.blogrestfulapi.model.User;
import com.fortunate.blogrestfulapi.response.*;

public interface UserService {
    RegisterResponse register (UserDto userDto);
    LoginResponse login (LoginDto loginDto);

    User findUserByEmail(String email);

    User findUserById(Long id);

}
