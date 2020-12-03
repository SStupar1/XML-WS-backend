package com.example.demo.services.impl;

import com.example.demo.dto.request.UpdateSimpleUserRequest;
import com.example.demo.dto.response.SimpleUserResponse;
import com.example.demo.entity.SimpleUser;
import com.example.demo.repository.ISimpleUserRepository;
import com.example.demo.repository.IUserRepository;
import com.example.demo.services.ISimpleUserService;
import com.example.demo.util.enums.RequestStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SimpleUserService implements ISimpleUserService {

    private final ISimpleUserRepository _simpleUserRepository;

    public SimpleUserService(ISimpleUserRepository simpleUserRepository) {
        _simpleUserRepository = simpleUserRepository;
    }


    @Override
    public SimpleUserResponse getSimpleUserById(Long id) {
        SimpleUser simpleUser = _simpleUserRepository.findOneById(id);
        if(simpleUser != null) {
            return mapSimpleUserToResponse(simpleUser);
        } else {
            return null;
        }
    }

    @Override
    public List<SimpleUserResponse> getRegistrationRequests() {
        List<SimpleUser> simpleUsers = _simpleUserRepository.findAllByRequestStatus(RequestStatus.PENDING);
        List<SimpleUserResponse> simpleUserResponses = new ArrayList<>();
        for (SimpleUser simpleUser: simpleUsers) {
            SimpleUserResponse simpleUserResponse = mapSimpleUserToResponse(simpleUser);
            simpleUserResponses.add(simpleUserResponse);
        }
        return simpleUserResponses;
    }

    @Override
    public void updateSimpleUserById(Long id, UpdateSimpleUserRequest request) {
        SimpleUser simpleUser = _simpleUserRepository.findOneById(id);
        if(request.getAddress() != null)
            simpleUser.setAddress(request.getAddress());
        if(request.getFirstName() != null)
            simpleUser.setFirstName(request.getFirstName());
        if(request.getLastName() != null)
            simpleUser.setLastName(request.getLastName());
        if(request.getSsn() != null)
            simpleUser.setSsn(request.getSsn());

        _simpleUserRepository.save(simpleUser);
    }

    private SimpleUserResponse mapSimpleUserToResponse(SimpleUser simpleUser){
        SimpleUserResponse simpleUserResponse = new SimpleUserResponse();
        simpleUserResponse.setId(simpleUser.getId());
        simpleUserResponse.setAddress(simpleUser.getAddress());
        simpleUserResponse.setFirstName(simpleUser.getFirstName());
        simpleUserResponse.setLastName(simpleUser.getLastName());
        simpleUserResponse.setSsn(simpleUser.getSsn());
        simpleUserResponse.setUsername(simpleUser.getUser().getUsername());
        simpleUserResponse.setUserRole(simpleUser.getUser().getUserRole().toString());
        return simpleUserResponse;
    }

}
