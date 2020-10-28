package com.example.demo.dto.response;

import lombok.Data;

import java.util.UUID;

@Data
public class UserResponse {

    private UUID id;

    private String username;

    private String token;

    private String userRole;

    private int tokenExpiresIn;
}
