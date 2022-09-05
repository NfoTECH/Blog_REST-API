package com.fortunate.blogrestfulapi.exception;

import lombok.Getter;

@Getter
public class PostAlreadyLikedException extends RuntimeException{
    private final String message;
    public PostAlreadyLikedException(String message) {
        this.message = message;
    }
}
