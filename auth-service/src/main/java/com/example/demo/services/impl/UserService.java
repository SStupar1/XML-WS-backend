package com.example.demo.services.impl;

import com.example.demo.dto.response.UserResponse;
import com.example.demo.entity.User;
import com.example.demo.repository.IAuthorityRepository;
import com.example.demo.repository.IUserRepository;
import com.example.demo.services.IUserService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class UserService implements IUserService {

    private final IUserRepository _userRepository;
    private final IAuthorityRepository _authorityRepository;

    public UserService(IUserRepository _userRepository, IAuthorityRepository _authorityRepository) {
        this._userRepository = _userRepository;
        this._authorityRepository = _authorityRepository;
    }

    @Override
    public List<UserResponse> getAllUsers() {
        List<User> users = _userRepository.findAllByDeleted(false);
        return users.stream().map(user -> mapUserToUserResponse(user)).collect(Collectors.toList());
    }

    @Override
    public User getOneUser(UUID id) {
        return _userRepository.findOneById(id);
    }

    private UserResponse mapUserToUserResponse(User user) {
        UserResponse userResponse = new UserResponse();
        userResponse.setId(user.getId());
        userResponse.setUsername(user.getUsername());
        return userResponse;
    }

}
