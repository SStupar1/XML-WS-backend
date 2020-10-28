package com.example.demo.controller;

import com.example.demo.dto.response.UserResponse;
import com.example.demo.security.TokenUtils;
import com.example.demo.services.IUserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/auth-service/users")
public class UserController {

    private final IUserService _userService;
    private final TokenUtils _tokenUtils;

    public UserController(IUserService userService, TokenUtils tokenUtils){
        this._userService = userService;
        _tokenUtils = tokenUtils;
    }

    @GetMapping
    public List<UserResponse> getAllUsers() {
        return _userService.getAllUsers();
    }

}
