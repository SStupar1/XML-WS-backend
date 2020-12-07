package com.example.demo.services.impl;

import com.example.demo.dto.request.GetIdRequest;
import com.example.demo.dto.request.UpdateSimpleUserRequest;
import com.example.demo.dto.response.SimpleUserResponse;
import com.example.demo.entity.SimpleUser;
import com.example.demo.repository.ISimpleUserRepository;
import com.example.demo.services.IEmailService;
import com.example.demo.services.ISimpleUserService;
import com.example.demo.util.enums.RequestStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SimpleUserService implements ISimpleUserService {

    private final ISimpleUserRepository _simpleUserRepository;

    private final IEmailService _emailService;

    public SimpleUserService(ISimpleUserRepository simpleUserRepository, IEmailService emailService) {
        _simpleUserRepository = simpleUserRepository;
        _emailService = emailService;
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

    @Override
    public void approveRegistrationRequest(GetIdRequest request) {
        SimpleUser simpleUser = _simpleUserRepository.findOneById(request.getId());
        simpleUser.setRequestStatus(RequestStatus.APPROVED);
        SimpleUser savedSimpleUser = _simpleUserRepository.save(simpleUser);

        _emailService.approveRegistrationMail(savedSimpleUser);
    }

    @Override
    public void confirmRegistrationRequest(GetIdRequest request) {
        SimpleUser simpleUser = _simpleUserRepository.findOneById(request.getId());
        simpleUser.setRequestStatus(RequestStatus.CONFIRMED);
        _simpleUserRepository.save(simpleUser);
    }

    @Override
    public void denyRegistrationRequest(GetIdRequest request) {
        SimpleUser simpleUser = _simpleUserRepository.findOneById(request.getId());
        simpleUser.setRequestStatus(RequestStatus.DENIED);
        SimpleUser savedSimpleUser = _simpleUserRepository.save(simpleUser);

        _emailService.denyRegistrationMail(savedSimpleUser);
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
