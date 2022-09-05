package com.fortunate.blogrestfulapi.exception;

import lombok.Getter;

@Getter
public class PostNotFoundException extends RuntimeException{
    private final String message;

    public PostNotFoundException(String message) {
        this.message = message;
    }
}
