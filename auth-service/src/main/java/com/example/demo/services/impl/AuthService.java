package com.example.demo.services.impl;

import com.example.demo.dto.request.LoginRequest;
import com.example.demo.dto.request.RegisterAgentRequest;
import com.example.demo.dto.request.RegistrationRequest;
import com.example.demo.dto.response.UserResponse;
import com.example.demo.entity.*;
import com.example.demo.repository.IAgentRepository;
import com.example.demo.repository.IAuthorityRepository;
import com.example.demo.repository.ISimpleUserRepository;
import com.example.demo.repository.IUserRepository;
import com.example.demo.security.TokenUtils;
import com.example.demo.services.IAuthService;
import com.example.demo.util.GeneralException;
import com.example.demo.util.enums.RequestStatus;
import com.example.demo.util.enums.UserRoles;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

@Service
public class AuthService implements IAuthService {

    private final TokenUtils _tokenUtils;

    private final IUserRepository _userRepository;

    private final PasswordEncoder _passwordEncoder;

    private final AuthenticationManager _authenticationManager;

    private final IAuthorityRepository _authorityRepository;

    private final ISimpleUserRepository _simpleUserRepository;

    private final IAgentRepository _agentRepository;



    public AuthService(TokenUtils tokenUtils, IUserRepository userRepository, PasswordEncoder passwordEncoder, AuthenticationManager authenticationManager, IAuthorityRepository authorityRepository, ISimpleUserRepository simpleUserRepository, IAgentRepository agentRepository) {
        _tokenUtils = tokenUtils;
        _userRepository = userRepository;
        _passwordEncoder = passwordEncoder;
        _authenticationManager = authenticationManager;
        _authorityRepository = authorityRepository;
        _simpleUserRepository = simpleUserRepository;
        _agentRepository = agentRepository;
    }

    @Override
    public String getPermission(String token) {
        String username = _tokenUtils.getUsernameFromToken(token);
        User user = _userRepository.findOneByUsername(username);
        String retVal = "";
        for (GrantedAuthority authority : user.getAuthorities()) {
            retVal += authority.getAuthority()+",";
        }
        return retVal.substring(0,retVal.length()-1);
    }

    @Override
    public UserResponse login(LoginRequest request) {
        User user = _userRepository.findOneByUsername(request.getUsername());

        if(user == null || !_passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new GeneralException("Bad credentials.", HttpStatus.BAD_REQUEST);
        }
        if(user.getSimpleUser() != null && user.getSimpleUser().getRequestStatus().equals(RequestStatus.PENDING)){
            throw new GeneralException("Your registration hasn't been approved yet.", HttpStatus.BAD_REQUEST);
        }
        if(user.getSimpleUser() != null && user.getSimpleUser().getRequestStatus().equals(RequestStatus.DENIED)){
            throw new GeneralException("Your registration has been denied.", HttpStatus.BAD_REQUEST);
        }
        if(user.getSimpleUser() != null && user.getSimpleUser().getRequestStatus().equals(RequestStatus.APPROVED)){
            throw new GeneralException("Your registration has been approved by admin. Please activate your account.", HttpStatus.BAD_REQUEST);
        }
        if(user.getSimpleUser() != null && user.getSimpleUser().isDeleted()) {
            throw new GeneralException("Your account is blocked by admin.", HttpStatus.BAD_REQUEST);
        }

        String username = request.getUsername();
        String password = request.getPassword();
        Authentication authentication = null;
        try {
            authentication = _authenticationManager
                    .authenticate(new UsernamePasswordAuthenticationToken(username, password));
        }catch (BadCredentialsException e){
            throw new GeneralException("Bad credentials.", HttpStatus.BAD_REQUEST);
        }catch (DisabledException e){
            throw new GeneralException("Your registration request hasn't been approved yet.", HttpStatus.BAD_REQUEST);
        }catch (Exception e) {
            e.printStackTrace();
        }

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = "";
        int expiresIn = 0;
        if(!user.isHasSignedIn()){
            MyUserDetails userLog = (MyUserDetails) authentication.getPrincipal();
            jwt = _tokenUtils.generateToken(userLog.getUsername());
            expiresIn = _tokenUtils.getExpiredIn();
        }
        UserResponse userResponse = mapUserToUserResponse(user);
        userResponse.setToken(jwt);
        userResponse.setTokenExpiresIn(expiresIn);

        return userResponse;
    }

    @Override
    public UserResponse registerSimpleUser(RegistrationRequest request) {
        if(!request.getPassword().equals(request.getRePassword())){
            throw new GeneralException("Passwords do not match.", HttpStatus.BAD_REQUEST);
        }

        User user = new User();
        SimpleUser simpleUser = new SimpleUser();
        user.setUsername(request.getUsername());
        user.setPassword(_passwordEncoder.encode(request.getPassword()));
        user.setUserRole(UserRoles.SIMPLE_USER);
        user.setHasSignedIn(false);
        List<Authority> authorities = new ArrayList<>();
        authorities.add(_authorityRepository.findOneByName("ROLE_SIMPLE_USER"));
        user.setAuthorities(new HashSet<>(authorities));

        simpleUser.setAddress(request.getAddress());
        simpleUser.setFirstName(request.getFirstName());
        simpleUser.setLastName(request.getLastName());
        simpleUser.setSsn(request.getSsn());
        simpleUser.setRequestStatus(RequestStatus.PENDING);
        simpleUser.setDeleted(false);
        simpleUser.setNumOfAds(0);
        SimpleUser savedSimpleUser = _simpleUserRepository.save(simpleUser);
        savedSimpleUser.setUser(user);
        user.setSimpleUser(savedSimpleUser);
        User savedUser = _userRepository.save(user);

        return mapUserToUserResponse(savedUser);

    }

    @Override
    public boolean registerAgent(RegisterAgentRequest request) {
        if(!request.getPassword().equals(request.getRePassword())){
            throw new GeneralException("Passwords do not match.", HttpStatus.BAD_REQUEST);
        }
        User user = new User();
        Agent agent = new Agent();

        if(request.getUsername().equals(_userRepository.findOneByUsername(request.getUsername()))){
            return false;
        }
        user.setUsername(request.getUsername());
        user.setPassword(_passwordEncoder.encode(request.getPassword()));
        user.setUserRole(UserRoles.AGENT);
        user.setHasSignedIn(false);
        List<Authority> authorities = new ArrayList<>();
        authorities.add(_authorityRepository.findOneByName("ROLE_AGENT"));
        user.setAuthorities(new HashSet<>(authorities));

        agent.setName(request.getName());
        agent.setBankAccountNumber(request.getBankAccountNumber());
        agent.setPib(request.getPib());
        agent.setAddress(request.getAddress());
        agent.setDateFounded(request.getDateFounded());
        Agent savedAgent = _agentRepository.save(agent);
        savedAgent.setUser(user);
        user.setAgent(savedAgent);
        _userRepository.save(user);

        return true;
    }

    private UserResponse mapUserToUserResponse(User user) {
        UserResponse userResponse = new UserResponse();
        if(user.getSimpleUser() != null){
            userResponse.setId(user.getSimpleUser().getId());
        }else if(user.getAgent() != null){
            userResponse.setId(user.getAgent().getId());
        }else if(user.getAdmin() != null){
            userResponse.setId(user.getAdmin().getId());
        }
        userResponse.setUsername(user.getUsername());
        if(user.getRoles().contains(_authorityRepository.findOneByName("ROLE_ADMIN"))){
            userResponse.setUserRole("ADMIN");
        }else if(user.getRoles().contains(_authorityRepository.findOneByName("ROLE_AGENT"))){
            userResponse.setUserRole("AGENT");
        }else if(user.getRoles().contains(_authorityRepository.findOneByName("ROLE_SIMPLE_USER"))){
            userResponse.setUserRole("SIMPLE_USER");
        }
        return userResponse;
    }
}
