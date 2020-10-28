package com.example.demo.services;


import com.example.demo.dto.response.UserResponse;
import com.example.demo.entity.User;

import java.util.List;
import java.util.UUID;

public interface IUserService {

    List<UserResponse> getAllUsers();

    User getOneUser(UUID id);

}
