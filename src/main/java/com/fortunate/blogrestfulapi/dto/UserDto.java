package com.fortunate.blogrestfulapi.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserDto {
    private String name;
    private String email;
    private String role;
    private String password;
}
