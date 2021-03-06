package com.example.demo.services;


import com.example.demo.dto.request.LoginRequest;
import com.example.demo.dto.request.RegisterAgentRequest;
import com.example.demo.dto.request.RegistrationRequest;
import com.example.demo.dto.response.UserResponse;

public interface IAuthService {

    String getPermission(String token);

    UserResponse login(LoginRequest request);

    UserResponse registerSimpleUser(RegistrationRequest request);

    boolean registerAgent(RegisterAgentRequest request);
}
