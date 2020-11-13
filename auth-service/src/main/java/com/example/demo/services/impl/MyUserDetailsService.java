package com.example.demo.services.impl;

import com.example.demo.entity.User;
import com.example.demo.entity.UserDetailsFactory;
import com.example.demo.repository.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetailsService implements UserDetailsService {

    private final IUserRepository _userRepository;

    @Autowired
    public MyUserDetailsService(IUserRepository userRepository) {
        this._userRepository = userRepository;
    }


    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        final User user = _userRepository.findOneByUsername(s);

        if (user == null) {
            throw new UsernameNotFoundException("User '" + s + "' not found");
        }
        return UserDetailsFactory.create(user);
    }
}
