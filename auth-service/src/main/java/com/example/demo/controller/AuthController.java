package com.example.demo.controller;

import com.example.demo.security.TokenUtils;
import javassist.NotFoundException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class AuthController {

    private final TokenUtils _tokenUtils;

    public AuthController(TokenUtils tokenUtils) {
        _tokenUtils = tokenUtils;
    }

    @GetMapping("/verify")
    public String verify(@RequestHeader("Auth-Token") String token) throws NotFoundException {
        return _tokenUtils.getUsernameFromToken(token);
    }

    @GetMapping("/hello")
    public String hello() {
        return "CAOOOO";
    }
}
