package com.example.demo;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name = "auth-service")
public interface IAuthClient {

    @GetMapping("/auth-service/verify")
    String verify(@RequestHeader("Auth-Token") String token);

    @GetMapping("/auth-service/permission")
    String getPermission(@RequestHeader("Auth-Token") String token);

}
