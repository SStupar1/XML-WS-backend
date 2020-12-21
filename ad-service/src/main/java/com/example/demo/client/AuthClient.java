package com.example.demo.client;

import com.example.demo.dto.client.Agent;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "auth-service")
public interface AuthClient {

    @GetMapping("/agents/{id}")
    Agent getAgent(@PathVariable("id") Long id);

}
