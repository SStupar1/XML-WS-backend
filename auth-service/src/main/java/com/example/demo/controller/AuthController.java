package com.example.demo.controller;

import com.example.demo.dto.request.LoginRequest;
import com.example.demo.dto.request.RegisterAgentRequest;
import com.example.demo.dto.request.RegistrationRequest;
import com.example.demo.dto.response.AgentResponse;
import com.example.demo.dto.response.UserResponse;
import com.example.demo.security.TokenUtils;
import com.example.demo.services.IAuthService;
import javassist.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final TokenUtils _tokenUtils;

    private final IAuthService _authService;

    public AuthController(TokenUtils tokenUtils, IAuthService authService) {
        _tokenUtils = tokenUtils;
        _authService = authService;
    }

    @GetMapping("/verify")
    public String verify(@RequestHeader("Auth-Token") String token) throws NotFoundException {
        return _tokenUtils.getUsernameFromToken(token);
    }

    @GetMapping("/permission")
    public String getPermissions(@RequestHeader("Auth-Token") String token) throws NotFoundException {
        return _authService.getPermission(token);
    }

    @GetMapping("/hello")
    public String hello() {
        return "CAOOOO";
    }

    @PutMapping("/login")
    public UserResponse login(@RequestBody LoginRequest request){
        return _authService.login(request);
    }

    @PostMapping("/register-simple-user")
    public UserResponse registerSimpleUser(@RequestBody RegistrationRequest request){
        return _authService.registerSimpleUser(request);
    }

    @PostMapping("/register-agent")
    //@PreAuthorize("hasAuthority('REGISTER')")
    public ResponseEntity<?> registerAgent(@RequestBody RegisterAgentRequest request){
        if(_authService.registerAgent(request)){
            return new ResponseEntity<>("Agent created !", HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>("User already exists !", HttpStatus.NOT_FOUND);
        }
    }
}
