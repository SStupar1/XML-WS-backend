package com.example.demo.client;

import com.example.demo.dto.client.Pricelist;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "rent-service")
public interface RentClient {

    @GetMapping("/{id}")
    public Pricelist getPricelist(@PathVariable("id") Long id);
}
