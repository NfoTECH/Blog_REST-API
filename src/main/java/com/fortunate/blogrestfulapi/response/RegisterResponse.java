package com.fortunate.blogrestfulapi.response;

import com.fortunate.blogrestfulapi.model.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

@Data @AllArgsConstructor @EqualsAndHashCode
public class RegisterResponse {
    private String message;
    private LocalDateTime timeStamp;
    private User user;
}
