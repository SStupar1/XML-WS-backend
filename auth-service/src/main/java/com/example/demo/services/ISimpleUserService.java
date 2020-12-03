package com.example.demo.services;

import com.example.demo.dto.request.UpdateSimpleUserRequest;
import com.example.demo.dto.response.SimpleUserResponse;

import java.util.List;

public interface ISimpleUserService {


    SimpleUserResponse getSimpleUserById(Long id);

    List<SimpleUserResponse> getRegistrationRequests();

    void updateSimpleUserById(Long id, UpdateSimpleUserRequest request);
}
