package com.example.demo.controller;

import com.example.demo.dto.request.GetIdRequest;
import com.example.demo.dto.request.UpdateSimpleUserRequest;
import com.example.demo.dto.response.SimpleUserResponse;
import com.example.demo.services.ISimpleUserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/simple-users")
public class SimpleUserController {

    private final ISimpleUserService _simpleUserService;

    public SimpleUserController(ISimpleUserService simpleUserService) {
        _simpleUserService = simpleUserService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getSimpleUser(@PathVariable("id") Long id){
        SimpleUserResponse simpleUserResponse = _simpleUserService.getSimpleUserById(id);
        if(simpleUserResponse != null) {
            return new ResponseEntity<>(simpleUserResponse, HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>("User doesn't exist.", HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{id}")
    public void updateSimpleUser(@PathVariable("id")Long id, @RequestBody UpdateSimpleUserRequest request){
        _simpleUserService.updateSimpleUserById(id, request);

    }

    @GetMapping("/registration-requests")
    @PreAuthorize("hasAuthority('REGISTER')")
    public List<SimpleUserResponse> getRegistrationRequests(){
        return _simpleUserService.getRegistrationRequests();
    }

    //admin odobrava
    @PutMapping("/approve")
    @PreAuthorize("hasAuthority('REGISTER')")
    public void approveRegistrationRequest(@RequestBody GetIdRequest request){
        _simpleUserService.approveRegistrationRequest(request);
    }

    @PutMapping("/deny")
    @PreAuthorize("hasAuthority('REGISTER')")
    public void denyRegistrationRequest(@RequestBody GetIdRequest request){
        _simpleUserService.denyRegistrationRequest(request);
    }

    //user potvrdjuje na mail-u
    @PutMapping("/confirm")
    public void confirmRegistrationRequest(@RequestBody GetIdRequest request){
        _simpleUserService.confirmRegistrationRequest(request);
    }

}
