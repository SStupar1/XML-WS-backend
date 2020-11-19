package com.example.demo.services;


import com.example.demo.dto.request.LoginRequest;
import com.example.demo.dto.response.UserResponse;

public interface IAuthService {

    String getPermission(String token);

    UserResponse login(LoginRequest request);
}
