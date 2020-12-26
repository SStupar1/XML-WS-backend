package com.example.demo.client;

import com.example.demo.dto.client.Agent;
import com.example.demo.dto.client.SimpleUser;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

@FeignClient(name = "auth-service")
public interface AuthClient {

    @GetMapping("/agents/{id}")
    Agent getAgent(@PathVariable("id") Long id);

    @GetMapping("/simple-users/{id}")
    SimpleUser getSimpleUser(@PathVariable("id") Long id);

    @PutMapping("/simple-users/{id}/increase")
    void increaseNumOfAds(@PathVariable("id") Long id);
}
