package com.example.demo.client;

import com.example.demo.dto.client.Ad;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "ad-service")
public interface AdClient {

    @GetMapping("/ads/{id}")
    Ad getAd(@PathVariable("id") Long id);


}
